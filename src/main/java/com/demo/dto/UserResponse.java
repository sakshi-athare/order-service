package com.demo.dto;

import lombok.Data;

@Data
public class UserResponse {
    private int userId;
    private String userName;
    private String email;
}
