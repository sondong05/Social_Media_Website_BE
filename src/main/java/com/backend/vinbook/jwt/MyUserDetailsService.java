package com.backend.vinbook.jwt;

import com.backend.vinbook.dto.UserDTO;
import com.backend.vinbook.entity.User;
import com.backend.vinbook.repository.UserRepository;
import com.backend.vinbook.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UserDTO dto = userService.getUser(username);
            return CustomerUserDetails.builder()
                    .id(dto.getId())
                    .email(dto.getEmail())
                    .username(dto.getUsername())
                    .password(dto.getPassword())
                    .role(dto.getRole())
//                    .profileId(dto.getProfileId())
                    .name(dto.getFullName())
                    .authorities(null).build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
