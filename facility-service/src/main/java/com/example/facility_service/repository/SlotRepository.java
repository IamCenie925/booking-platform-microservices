package com.example.facility_service.repository;

import com.example.facility_service.model.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface SlotRepository extends JpaRepository<Slot, Long> {
    List<Slot> findByFacilityIdAndAvailableTrue(Long facilityId); // Tìm các slot trống theo facilityId
    
}
