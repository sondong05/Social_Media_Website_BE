package com.backend.vinbook.service;

import com.backend.vinbook.dto.ChangePasswordDTO;
import com.backend.vinbook.dto.UserDTO;
import com.backend.vinbook.entity.User;

public interface UserService {
    void registerUser(UserDTO userDTO);
    UserDTO getUser(String username);
    void changePassword(ChangePasswordDTO dto);
}
