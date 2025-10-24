package com.backend.vinbook.service;

import com.backend.vinbook.dto.ChangePasswordDTO;
import com.backend.vinbook.dto.ForgotPasswordDTO;
import com.backend.vinbook.dto.UserDTO;
import com.backend.vinbook.entity.Profile;
import com.backend.vinbook.entity.Role;
import com.backend.vinbook.entity.User;
import com.backend.vinbook.repository.ProfileRepository;
import com.backend.vinbook.repository.UserRepository;
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
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void registerUser(UserDTO userDTO) {
        Optional<User> opt = userRepository.findByUsername(userDTO.getUsername());

        if (opt.isPresent()) {
            throw new RuntimeException("Username đã tồn tại");
        }
        // đảm bảo role cố định là USER (admin không thể tạo admin qua endpoint này)
//        userDTO.setRole(Role.USER);
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        Profile profile = Profile.builder()
                .email(userDTO.getEmail())
                .fullName(userDTO.getFullName())
                .username(userDTO.getUsername())
                .build();
        profileRepository.save(profile);
        userRepository.save(userDTO.toEntity());
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
        Map<String, Object> model = Map.of(
                "username", currentUser.getFullName(),
                "resetLink", "https://www.youtube.com/"
        );
        try {
            emailService.sendDefault(dto.getEmail(), subject, "email/confirm-change-pass-email.html", model);
        } catch (MessagingException e) {
            throw new RuntimeException("Loi gui email");
        }
    }


}
