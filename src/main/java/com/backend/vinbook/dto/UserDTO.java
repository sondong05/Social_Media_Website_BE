package com.backend.vinbook.dto;

import com.backend.vinbook.entity.Role;
import com.backend.vinbook.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDTO {
    private Long id;

    @NotBlank(message = "Tên đăng nhập không được để trống")
    @Size(min = 8, message = "Tên đăng nhập phải có ít nhất 8 ký tự")
    private String username;

    @NotBlank(message = "Mật khẩu không được để trống")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$",
            message = "Mật khẩu phải có ít nhất 8 ký tự, bao gồm chữ hoa, chữ thường và chữ số."
    )
    private String password;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    private String email;

    private String fullName;
    private String gender;
    private LocalDate birthDate;

    private Role role;
    private boolean isFirstLogin = true;

    public User toEntity() {
        User user = new User();
        user.setId(this.id);
        user.setUsername(this.username);
        user.setPassword(this.password);
        user.setEmail(this.email);
        user.setFullName(this.fullName);
        user.setGender(this.gender);
        user.setBirthDate(this.birthDate);
        user.setRole(Role.USER);
        user.setFirstLogin(this.isFirstLogin);
        return user;
    }

}
