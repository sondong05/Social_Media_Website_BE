package com.backend.vinbook.controller;

import com.backend.vinbook.dto.ChangePasswordDTO;
import com.backend.vinbook.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/change-password")
    // Yêu cầu người dùng phải được xác thực (đã đăng nhập) để gọi API này
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> changePassword(@RequestBody @Valid ChangePasswordDTO changePasswordDTO) {
        try {
            userService.changePassword(changePasswordDTO);
            return ResponseEntity.ok("Đổi mật khẩu thành công!");
        } catch (IllegalArgumentException e) {
            // Bắt các lỗi validation logic từ service và trả về lỗi 400
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
