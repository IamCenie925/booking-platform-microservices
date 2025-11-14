package com.example.facility_service.repository;

import com.example.facility_service.model.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacilityRepository extends JpaRepository<Facility, Long> {
    List<Facility> findByAddressContainingIgnoreCase(String address); // Tìm các facility theo địa chỉ (không phân biệt hoa thường)
}
