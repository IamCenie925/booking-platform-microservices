package com.example.facility_service.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Data
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String address;
    private String type; // loại hình sân bãi (ví dụ: sân bóng đá, sân tennis, v.v.)
    private BigDecimal pricePerHour; // Giá mỗi giờ
}
