package com.example.facility_service.controller;

import com.example.facility_service.model.Facility;
import com.example.facility_service.model.Slot;
import com.example.facility_service.repository.FacilityRepository;
import com.example.facility_service.repository.SlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facilities")
@RequiredArgsConstructor

public class FacilityController {
    private final FacilityRepository facilityRepo;
    private final SlotRepository slotRepo;

    @GetMapping("/search") //API tìm kiếm sân theo địa điểm
    public List<Facility> searchFacilities(@RequestParam String location) {
        return facilityRepo.findByAddressContainingIgnoreCase(location);
    }

    @GetMapping("/{id}/slots") //API lấy các khung giờ trống của sân theo facilityId
    public List<Slot> getAvailableSlots(@PathVariable Long id) {
        return slotRepo.findByFacilityIdAndAvailableTrue(id);
    }
    
    // (thêm API POST để admin tạo sân/slot sau, bây giờ cần test dữ liệu trước)
}
