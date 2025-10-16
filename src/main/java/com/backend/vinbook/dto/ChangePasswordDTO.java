package com.backend.vinbook.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChangePasswordDTO {
    @NotBlank(message = "Mật khẩu cũ không được để trống")
    private String oldPassword;

    @NotBlank(message = "Mật khẩu mới không được để trống")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$",
            message = "Mật khẩu mới phải có ít nhất 8 ký tự, bao gồm chữ hoa, chữ thường và chữ số."
    )
    private String newPassword;

    @NotBlank(message = "Mật khẩu xác nhận không được để trống")
    private String confirmPassword;
}
