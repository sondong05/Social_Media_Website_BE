package com.backend.vinbook.controller;

import com.backend.vinbook.dto.LoginDTO;
import com.backend.vinbook.dto.UserDTO;
import com.backend.vinbook.jwt.JwtUtil;
import com.backend.vinbook.service.AuthService;
import com.backend.vinbook.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Validated
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody @Valid UserDTO userDTO) {
        try {
            userService.registerUser(userDTO);
            return new ResponseEntity<>("Tạo tài khoản thành công", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // Sẽ trả về "Username đã tồn tại" với status 400
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody @Valid LoginDTO loginDTO) {
        log.info(loginDTO.getUsername());
        // log.info(loginDTO.getPassword()); // Nên hạn chế log password plaintext

        try {
            String jwt = authService.login(loginDTO);
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        } catch (AuthenticationException e) {
            // Bắt lỗi khi username hoặc password sai
            return ResponseEntity.badRequest().body("Thông tin đăng nhập không chính xác");
        }
    }
}