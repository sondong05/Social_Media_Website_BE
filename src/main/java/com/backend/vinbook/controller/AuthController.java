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
@RequestMapping("/api/v1/auth/")
@Validated
@RequiredArgsConstructor
@Slf4j

public class AuthController {
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDTO loginDTO) {

        log.info(loginDTO.getUsername());
        log.info(loginDTO.getPassword());
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),
                            loginDTO.getPassword())
            );

        } catch (AuthenticationException e) {
            throw new RuntimeException("Loi");
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginDTO.getUsername());
        final  String jwt = jwtUtil.generateToken(userDetails);
        return new ResponseEntity<>(jwt, HttpStatus.OK);

    }
}
