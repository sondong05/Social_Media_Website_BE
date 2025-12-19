package com.backend.vinbook.controller;

import com.backend.vinbook.dto.ChangePasswordDTO;
import com.backend.vinbook.dto.ForgotPasswordDTO;
import com.backend.vinbook.dto.ResetPasswordDTO;
import com.backend.vinbook.service.OtpService;
import com.backend.vinbook.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private  final OtpService otpService;
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

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody @Valid ForgotPasswordDTO email) {

        try {
            userService.sendEmailToChangePass(email);
            return ResponseEntity.ok("Gửi mail thành công");
        } catch (IllegalArgumentException e) {
            // Bắt các lỗi validation logic từ service và trả về lỗi 400
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e) {
            // Log lỗi ra console để debug trên Render
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Lỗi hệ thống: " + e.getMessage());
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordDTO dto) {
        try {
            // Gọi service xử lý logic
            userService.updatePassword(dto);
            return ResponseEntity.ok("Đổi mật khẩu thành công!");

        } catch (IllegalArgumentException e) {
            // QUAN TRỌNG: Bắt lỗi IllegalArgumentException từ Service (Email ko tồn tại, sai OTP...)
            // Và trả về 400 Bad Request kèm message
            return ResponseEntity.badRequest().body(e.getMessage());

        } catch (Exception e) {
            // Dự phòng cho các lỗi hệ thống khác không lường trước được
            return ResponseEntity.internalServerError().body("Lỗi hệ thống: " + e.getMessage());
        }
    }


}
