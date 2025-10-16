package com.backend.vinbook.service;

import com.backend.vinbook.dto.UserDTO;

public interface UserService {
    void registerUser(UserDTO userDTO);
    UserDTO getUser(String username);

}
