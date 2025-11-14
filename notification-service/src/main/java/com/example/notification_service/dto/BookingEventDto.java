package com.example.notification_service.dto;

import java.time.Instant;

/**
 * Đây là DTO "hứng" dữ liệu JSON từ RabbitMQ.
 * Tên các trường (field) phải khớp với JSON được gửi từ Booking Service.
 */
public record BookingEventDto(
    Long id,
    Long userId,
    Long facilityId,
    Long slotId,
    String status,
    Instant createdAt
) {}