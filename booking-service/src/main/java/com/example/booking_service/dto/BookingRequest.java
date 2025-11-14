package com.example.booking_service.dto;

// Đây là data client gửi lên, chứa các trường cần thiết để tạo booking, không có id vì id do hệ thống tạo tự động, cũng không có trạng thái vì mặc định khi tạo booking sẽ là PENDING, các trường khác do client cung cấp
public record BookingRequest(
    Long userId,
    Long facilityId,
    Long slotId
) {}
