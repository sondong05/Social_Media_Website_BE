package com.backend.vinbook.controller;

import com.backend.vinbook.dto.ForgotPasswordDTO;
import com.backend.vinbook.dto.ProfileDTO;
import com.backend.vinbook.dto.UpdateProfileDTO;
import com.backend.vinbook.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;
    @PostMapping("/update")
    public ResponseEntity<String> updateProfile(@RequestBody @Valid UpdateProfileDTO updateProfileDTO) {

        try {
            profileService.updateProfile(updateProfileDTO);

            return ResponseEntity.ok("Update successfully");
        } catch (IllegalArgumentException e) {
            // Bắt các lỗi validation logic từ service và trả về lỗi 400
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/detail")
    public ResponseEntity<ProfileDTO> getProfile() {
        // Chỉ gọi 1 lần và trả về kết quả ngay
        return ResponseEntity.ok(profileService.getProfile());
    }
}
