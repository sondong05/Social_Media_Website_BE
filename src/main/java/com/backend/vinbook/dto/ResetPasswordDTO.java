package com.backend.vinbook.dto;

import lombok.Data;

@Data
public class ResetPasswordDTO {
    private String email;
    private String newPassword;
    private String confirmNewPassword;
    private String otp;
}
