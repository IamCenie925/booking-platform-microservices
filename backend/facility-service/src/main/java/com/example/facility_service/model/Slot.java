package com.example.facility_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.time.Instant;

@Entity
@Data
public class Slot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Quan hệ nhiều-một: nhiều Slot thuộc 1 Facility
    @ManyToOne(fetch = FetchType.LAZY) // Tải chậm để tối ưu hiệu năng
    @JoinColumn(name = "facility_id") // Khóa ngoại tham chiếu đến Facility
    @JsonIgnore // Tránh vòng lặp vô hạn khi serializing JSON
    private Facility facility;

    private Instant startTime;
    private Instant endTime;
    private boolean available = true; // Trạng thái (trống hay không)
}
