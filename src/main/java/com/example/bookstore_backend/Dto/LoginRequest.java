package com.example.bookstore_backend.Dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
