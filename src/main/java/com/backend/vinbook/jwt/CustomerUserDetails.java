package com.backend.vinbook.jwt;

import com.backend.vinbook.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerUserDetails implements UserDetails {
    private Long id;
    private String username;
    private String email;
    private String password;
    private Role role;
    private String name;
    private Long profileId;
    private Collection<? extends GrantedAuthority> authorities;
}
