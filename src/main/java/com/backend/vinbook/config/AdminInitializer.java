package com.backend.vinbook.config;

import com.backend.vinbook.entity.Role;
import com.backend.vinbook.entity.User;
import com.backend.vinbook.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;

@Configuration
public class AdminInitializer {

    @Bean
    CommandLineRunner initAdmin(UserRepository userRepository) {
        return args -> {
            String adminUsername = "sondongnguyen";
            String defaultPassword = "Sondong@23092005";
            Role role = Role.ADMIN;

            // Kiểm tra xem admin đã tồn tại chưa
            if (userRepository.findByUsername(adminUsername).isEmpty()) {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                String encodedPassword = encoder.encode(defaultPassword);

                User admin = User.builder()
                        .username(adminUsername)
                        .password(encodedPassword)
                        .isFirstLogin(false)
                        .fullName("Nguyen Son Dong")
                        .birthDate(LocalDate.parse("2005-09-23"))
                        .role(role)
                        .gender("Nam")
                        .email("sondong23092005@gmail.com")
                        .build();

                userRepository.save(admin);
                System.out.println("✅ Tạo tài khoản admin mặc định: admin / Admin@123");
            } else {
                System.out.println("ℹ️ Admin mặc định đã tồn tại, bỏ qua khởi tạo.");
            }
        };
    }
}
