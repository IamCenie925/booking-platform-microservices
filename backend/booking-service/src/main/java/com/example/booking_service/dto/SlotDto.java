package com.example.booking_service.dto;

import java.time.Instant;

// Đây là data service booking trả về cho client, dùng để hứng data từ service facility, phải có các trường giống với Slot trong facility-service
public record SlotDto(
    Long id,
    Instant startTime,
    Instant endTime,
    boolean available
) {}
