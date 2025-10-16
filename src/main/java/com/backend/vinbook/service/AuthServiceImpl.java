package com.backend.vinbook.service;

import com.backend.vinbook.dto.LoginDTO;
import com.backend.vinbook.jwt.JwtUtil;
import com.backend.vinbook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    @Override
    public String login(LoginDTO loginDTO) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDTO.getUsername(), loginDTO.getPassword()));

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginDTO.getUsername());
        return jwtUtil.generateToken(userDetails);
    }
}
