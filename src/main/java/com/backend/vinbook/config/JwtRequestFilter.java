package com.backend.vinbook.config;

import com.backend.vinbook.entity.User;
import com.backend.vinbook.jwt.JwtUtil;
import com.backend.vinbook.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
//    private final MyUserDetailsService userDetailsService;
    private UserRepository userRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);
            try {
                username = jwtUtil.extractUsername(jwt);
            } catch (Exception e) {
                // log lỗi nếu cần
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            User user  =userRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User ko tim thay"));
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        user, null, null);

                SecurityContextHolder.getContext().setAuthentication(authToken);

        }

        filterChain.doFilter(request, response);
    }
}
