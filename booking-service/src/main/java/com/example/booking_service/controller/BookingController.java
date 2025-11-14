package com.example.booking_service.controller;

import com.example.booking_service.dto.BookingRequest;
import com.example.booking_service.model.Booking;
import com.example.booking_service.service.BookingFlowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController //sử dụng để đánh dấu lớp này là một REST controller, cho phép nó xử lý các yêu cầu HTTP và trả về dữ liệu dưới dạng JSON hoặc XML
@RequestMapping("/api/bookings")
@RequiredArgsConstructor //tự động tạo constructor với các tham số là các trường final, giúp tiêm phụ thuộc (dependency injection) dễ dàng hơn

public class BookingController {
    private final BookingFlowService bookingFlowService;

    // Endpoint để tạo booking mới
    @PostMapping("/create") // Xử lý yêu cầu POST đến /api/bookings/create
    @ResponseStatus(HttpStatus.CREATED)
    public Booking createBooking(
            @RequestBody BookingRequest request, // Lấy dữ liệu từ body của yêu cầu và ánh xạ nó vào đối tượng BookingRequest
            @RequestHeader("Authorization") String authToken) {
        // Lấy token từ header Authorization, sau đó chuyển tiếp token này khi gọi các dịch vụ khác, để xác thực người dùng
        return bookingFlowService.createBooking(request, authToken);
    }
}
