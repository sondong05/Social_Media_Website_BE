package com.backend.vinbook.service.impl;

import com.backend.vinbook.dto.ChangePasswordDTO;
import com.backend.vinbook.dto.ForgotPasswordDTO;
import com.backend.vinbook.dto.ResetPasswordDTO;
import com.backend.vinbook.dto.UserDTO;
import com.backend.vinbook.entity.Profile;
import com.backend.vinbook.entity.Role;
import com.backend.vinbook.entity.User;
import com.backend.vinbook.repository.ProfileRepository;
import com.backend.vinbook.repository.UserRepository;
import com.backend.vinbook.service.EmailService;
import com.backend.vinbook.service.OtpService;
import com.backend.vinbook.service.UserService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final ProfileRepository profileRepository;
    private final OtpService otpService;
    @Override
    public void registerUser(UserDTO userDTO) {
        // 1. Kiểm tra Username tồn tại
        Optional<User> opt = userRepository.findByUsername(userDTO.getUsername());
        if (opt.isPresent()) {
            throw new RuntimeException("Username đã tồn tại");
        }

        // 2. Mã hóa mật khẩu
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        // 3. Tạo và lưu Profile trước
        Profile profile = Profile.builder()
                .email(userDTO.getEmail())
                .fullName(userDTO.getFullName())
                .username(userDTO.getUsername())
                .gender(userDTO.getGender())
                .birthDate(userDTO.getBirthDate())
                .build();
        profileRepository.save(profile);

        // 4. SỬA LỖI: Tạo User entity và set ID = null
        User newUser = userDTO.toEntity();
        newUser.setId(null); // <--- Quan trọng: Bắt buộc set null để Hibernate hiểu là INSERT

        // 5. Lưu User vào DB
        userRepository.save(newUser);
    }

    @Override
    public UserDTO getUser(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new RuntimeException("Username ko thấy")).toDTO();
    }

    @Override
    public void changePassword(ChangePasswordDTO dto) {
        // 1. Lấy username của người dùng đang đăng nhập từ Security Context
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));

        // 2. Kiểm tra mật khẩu cũ có khớp không
        if (!passwordEncoder.matches(dto.getOldPassword(), currentUser.getPassword())) {
            throw new IllegalArgumentException("Mật khẩu cũ không chính xác.");
        }

        // 3. Kiểm tra mật khẩu mới và mật khẩu xác nhận có giống nhau không
        if (!dto.getNewPassword().equals(dto.getConfirmPassword())) {
            throw new IllegalArgumentException("Mật khẩu mới và mật khẩu xác nhận không khớp.");
        }

        // 4. Kiểm tra mật khẩu mới có trùng với mật khẩu cũ không
        if (passwordEncoder.matches(dto.getNewPassword(), currentUser.getPassword())) {
            throw new IllegalArgumentException("Mật khẩu mới không được trùng với mật khẩu cũ.");
        }

        // 5. Mã hóa và cập nhật mật khẩu mới
        currentUser.setPassword(passwordEncoder.encode(dto.getNewPassword()));

        // 6. Đánh dấu tài khoản không còn là đăng nhập lần đầu
        currentUser.setFirstLogin(false);

        // 7. Lưu lại vào DB
        userRepository.save(currentUser);

    }

    @Override
    public void sendEmailToChangePass(ForgotPasswordDTO dto) {
        log.info("Email: {}", dto);
        User currentUser = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));
        log.info("User: {}", currentUser);
        String subject = "Xác nhan de doi mat khau";
        String  otp = otpService.generateOtp(dto.getEmail());
        Map<String, Object> model = Map.of(
                "username", currentUser.getFullName(),
                "otp", otp
        );
        try {
            emailService.sendDefault(dto.getEmail(), subject, "email/confirm-change-pass-email", model);
        } catch (MessagingException e) {
            throw new RuntimeException("Loi gui email");
        }
    }

    @Override
    public void updatePassword(ResetPasswordDTO resetPasswordDTO) {
        // 1. Kiểm tra Email
        User currentUser = userRepository.findByEmail(resetPasswordDTO.getEmail())
                // Cập nhật thông báo Email
                .orElseThrow(() -> new IllegalArgumentException("Email không tồn tại trong hệ thống"));

        // 2. Kiểm tra OTP
        boolean verified = otpService.verifyOtp(resetPasswordDTO.getEmail(), resetPasswordDTO.getOtp());
        if (!verified) {
            // Cập nhật thông báo OTP
            throw new IllegalArgumentException("Mã OTP không chính xác hoặc đã hết hạn. Vui lòng kiểm tra lại hoặc yêu cầu gửi lại mã.");
        }



        // 3. Kiểm tra mật khẩu khớp nhau
        if (!resetPasswordDTO.getNewPassword().equals(resetPasswordDTO.getConfirmNewPassword())) {
            // Cập nhật thông báo mật khẩu
            throw new IllegalArgumentException("Mật khẩu xác nhận không trùng khớp");
        }

        String encodedPassword = passwordEncoder.encode(resetPasswordDTO.getNewPassword());
        currentUser.setPassword(encodedPassword);
        userRepository.save(currentUser);
    }


}
