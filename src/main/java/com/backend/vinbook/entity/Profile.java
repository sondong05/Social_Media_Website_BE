package com.backend.vinbook.entity;

import com.backend.vinbook.dto.ProfileDTO;
import com.backend.vinbook.dto.UserDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String fullName;
    private String gender;
    private LocalDate birthDate;

    public ProfileDTO toDTO() {
        return ProfileDTO.builder()
                .id(this.id)
                .username(this.username)
                .email(this.email)
                .fullName(fullName)
                .gender(gender)
                .birthDate(this.birthDate)
                .build();
    }


}