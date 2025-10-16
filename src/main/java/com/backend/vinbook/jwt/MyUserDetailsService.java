//package com.backend.vinbook.jwt;
//
//import com.backend.vinbook.dto.UserDTO;
//import com.backend.vinbook.service.UserService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class MyUserDetailsService implements UserDetailsService {
//    private final UserService userService;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        try {
//            UserDTO dto = userService.getUser(username);
//            String roleName = dto.getRole().name(); // ví dụ "ADMIN"
//            if (!roleName.startsWith("ROLE_")) {
//                roleName = "ROLE_" + roleName;
//            }
//            System.out.println(roleName);
//            List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(roleName));
//            return CustomerUserDetails.builder()
//                    .id(dto.getId())
//                    .email(dto.getEmail())
//                    .username(dto.getUsername())
//                    .password(dto.getPassword(  ))
//                    .role(dto.getRole())
////                    .profileId(dto.getProfileId())
//                    .name(dto.getFullName())
//                    .authorities(authorities).build();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//}