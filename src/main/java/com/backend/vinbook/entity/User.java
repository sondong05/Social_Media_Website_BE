package com.backend.vinbook.entity;

import com.backend.vinbook.dto.UserDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password; // lưu hashed
    private String email;
    private String fullName;
    private String gender;
    private LocalDate birthDate;
    private Role role; // "ADMIN" / "USER"
    private boolean isFirstLogin = true;

    public UserDTO toDTO() {
        return UserDTO.builder()
                .id(this.id)
                .username(this.username)
                .email(this.email)
                .fullName(fullName)
                .password(this.password)
                .role(this.role)
                .gender(gender)
                .birthDate(this.birthDate)
                .isFirstLogin(isFirstLogin)
                .build();
    }


}