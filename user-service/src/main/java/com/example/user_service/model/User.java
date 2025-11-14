package com.example.user_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Table(name = "app_user") // Đặt tên bảng là "app_user" vì "user" là từ khóa của Postgres
@Data // Lombok: tự tạo getters, setters...

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotEmpty(message = "Username không được để trống")
    private String username;

    @Column(nullable = false)
    @NotEmpty(message = "Password không được để trống")
    private String password; // Mật khẩu demo, chưa băm (hash)

    @Email(message = "Email không hợp lệ")
    private String email;
    
    private String phone;
    
    private String role = "USER"; // Gán vai trò mặc định
}
