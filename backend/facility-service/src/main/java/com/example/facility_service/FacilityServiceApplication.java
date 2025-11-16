package com.example.facility_service;

import com.example.facility_service.model.Facility;
import com.example.facility_service.model.Slot;
import com.example.facility_service.repository.FacilityRepository;
import com.example.facility_service.repository.SlotRepository;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient // Kích hoạt tính năng đăng ký dịch vụ với Eureka
@SpringBootApplication
public class FacilityServiceApplication {
	//fix timezone :)))
	static {
			java.util.TimeZone.setDefault(java.util.TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
		} // Set múi giờ mặc định cho toàn ứng dụng

	public static void main(String[] args) {
		SpringApplication.run(FacilityServiceApplication.class, args);
	}


	@Bean
	CommandLineRunner initData(FacilityRepository facilityRepo, SlotRepository slotRepo) {
        return args -> {
            // Chỉ thêm data nếu database trống, tránh trùng lặp khi khởi động lại
            if (facilityRepo.count() > 0) {
                System.out.println("Sample data already exists, skipping seed.");
                return;
            }

            System.out.println(">>> >>> Seeding data...");

            // 1. Tạo Sân 1 (Sân bóng ở Đà Nẵng)
            Facility facility1 = new Facility();
            facility1.setName("FPT Football Pitch");
            facility1.setAddress("FPT City, Da Nang");
            facility1.setType("Football");
            facility1.setPricePerHour(new BigDecimal("150000.00"));
            facilityRepo.save(facility1); 

            // 2. Tạo Sân 2 (Sân cầu lông ở Đà Nẵng)
            Facility facility2 = new Facility();
            facility2.setName("V-Sports Badminton Court");
            facility2.setAddress("Near Dragon Bridge, Da Nang");
            facility2.setType("Badminton");
            facility2.setPricePerHour(new BigDecimal("80000.00"));
            facilityRepo.save(facility2); 

            // 3. Tạo Sân 3 (Sân tennis ở Hội An)
            Facility facility3 = new Facility();
            facility3.setName("Hoi An Resort Tennis Court");
            facility3.setAddress("Cua Dai, Hoi An");
            facility3.setType("Tennis");
            facility3.setPricePerHour(new BigDecimal("200000.00"));
            facilityRepo.save(facility3); 

            // 4. Tạo các khung giờ (Slots) cho Sân 1 (FPT)
            Instant now = Instant.now(); 

            Slot slot1_1 = new Slot();
            slot1_1.setFacility(facility1);
            slot1_1.setStartTime(now.plus(1, ChronoUnit.HOURS));
            slot1_1.setEndTime(now.plus(2, ChronoUnit.HOURS));
            slot1_1.setAvailable(true);
            slotRepo.save(slot1_1);

            Slot slot1_2 = new Slot();
            slot1_2.setFacility(facility1); 
            slot1_2.setStartTime(now.plus(2, ChronoUnit.HOURS)); 
            slot1_2.setEndTime(now.plus(3, ChronoUnit.HOURS));  
            slot1_2.setAvailable(true);
            slotRepo.save(slot1_2);

            Slot slot1_3_booked = new Slot();
            slot1_3_booked.setFacility(facility1); 
            slot1_3_booked.setStartTime(now.plus(3, ChronoUnit.HOURS));
            slot1_3_booked.setEndTime(now.plus(4, ChronoUnit.HOURS));
            slot1_3_booked.setAvailable(false); // Slot này đã bị đặt (false)
            slotRepo.save(slot1_3_booked);

            System.out.println(">>> Finished seeding 3 Facilities and 3 Slots.");
        };
    }
}
