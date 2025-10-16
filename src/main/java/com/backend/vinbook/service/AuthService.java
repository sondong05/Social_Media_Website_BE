package com.backend.vinbook.service;

import com.backend.vinbook.dto.LoginDTO;

public interface AuthService {
    String login(LoginDTO loginDTO);
}
