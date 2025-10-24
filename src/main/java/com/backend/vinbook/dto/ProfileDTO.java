package com.backend.vinbook.dto;

import com.backend.vinbook.entity.Profile;
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
public class ProfileDTO {
    private Long id;

    @NotBlank(message = "Tên đăng nhập không được để trống")
    @Size(min = 8, message = "Tên đăng nhập phải có ít nhất 8 ký tự")
    private String username;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    private String email;

    private String fullName;
    private String gender;
    private LocalDate birthDate;


    public Profile toEntity() {
        Profile user = new Profile();
        user.setId(this.id);
        user.setUsername(this.username);
        user.setEmail(this.email);
        user.setFullName(this.fullName);
        user.setGender(this.gender);
        user.setBirthDate(this.birthDate);
        return user;
    }

}
