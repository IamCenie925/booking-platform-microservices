package com.example.booking_service.service;

import com.example.booking_service.config.RabbitMQConfig;
import com.example.booking_service.dto.BookingRequest;
import com.example.booking_service.dto.SlotDto;
import com.example.booking_service.model.Booking;
import com.example.booking_service.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor // Tự động tạo constructor với các tham số là các field final
public class BookingFlowService {
    private final BookingRepository bookingRepo;
    private final WebClient.Builder webClientBuilder;
    // (Sau này sẽ tiêm AmqpTemplate để gửi RabbitMQ ở đây)
    private final RabbitTemplate rabbitTemplate;

    public Booking createBooking(BookingRequest request, String authToken) {
        
        // --- BƯỚC 1: Gọi User-Service (Demo) ---
        // (Trong thực tế sẽ POST /api/auth/validate-token
        //  nhưng giờ chỉ GET /api/users/{id} để kiểm tra)
        
        // Thử gọi API /api/users/{id}
        // .block() = Chạy đồng bộ (chờ kết quả)
        try {
            webClientBuilder.build().get()
                .uri("lb://USER-SERVICE/api/users/{userId}", request.userId())
                .header("Authorization", authToken) // Chuyển tiếp token
                .retrieve()
                .bodyToMono(String.class) // Lấy body (demo, không cần dùng)
                .block(); 
        } catch (Exception e) {
            throw new RuntimeException("User service call failed or User not found!");
        }
        
        // --- BƯỚC 2: Gọi Facility-Service ---
        // Lấy tất cả các slot trống của sân
        List<SlotDto> availableSlots = webClientBuilder.build().get()
            .uri("lb://FACILITY-SERVICE/api/facilities/{facilityId}/slots", request.facilityId())
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<List<SlotDto>>() {})
            .block();

        // --- BƯỚC 3: Kiểm tra xem slotId có hợp lệ không ---
        Optional<SlotDto> requestedSlot = availableSlots.stream()
            .filter(slot -> slot.id().equals(request.slotId()) && slot.available())
            .findFirst();

        if (requestedSlot.isEmpty()) {
            // Nếu slotId không có trong danh sách slot trống -> Báo lỗi
            throw new RuntimeException("Slot is not available or does not exist.");
        }

        // --- BƯỚC 4: Lưu Booking vào DB ---
        // (Race condition: 2 người cùng đặt 1 lúc. Sẽ fix sau)
        Booking newBooking = new Booking();
        newBooking.setUserId(request.userId());
        newBooking.setFacilityId(request.facilityId());
        newBooking.setSlotId(request.slotId());
        newBooking.setStatus("CREATED"); // Trạng thái ban đầu
        
        Booking savedBooking = bookingRepo.save(newBooking);

        // --- BƯỚC 5: Gửi sự kiện (event) sang RabbitMQ ---
        // 1. Gọi API của Facility Service để "đánh dấu" slot là unavailable=false
        // 2. Gửi sự kiện (event) sang Notification Service qua RabbitMQ
        System.out.println(">>> [Booking Service] Publishing booking created event...");
        // Gửi "savedBooking" (Spring sẽ tự chuyển thành JSON)
        // vào queue tên là RabbitMQConfig.QUEUE_NAME
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, savedBooking);
        return savedBooking;
    }
}
