package com.example.booking_service.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.Instant;

@Entity
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long userId;
    private Long facilityId;
    private Long slotId;
    private String status; // "CREATED", "CONFIRMED", "CANCELLED"
    private Instant createdAt = Instant.now();
}
