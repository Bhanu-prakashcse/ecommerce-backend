package com.bhanu.ecommerce_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class RegisterRequest {
    private String username;
    private String email;
    private String password;
}





