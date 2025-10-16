package com.backend.vinbook.controller;

import com.backend.vinbook.dto.LoginDTO;
import com.backend.vinbook.dto.UserDTO;
import com.backend.vinbook.jwt.JwtUtil;
import com.backend.vinbook.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/")
@Validated
@RequiredArgsConstructor
@Slf4j

public class AdminController {

    private final UserService userService;
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody @Valid UserDTO userDTO) {
        userService.registerUser(userDTO);
        return new ResponseEntity<>(("Account created"), HttpStatus.CREATED);

    }

}
