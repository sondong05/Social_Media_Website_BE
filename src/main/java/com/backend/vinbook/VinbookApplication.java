package com.backend.vinbook;

import com.backend.vinbook.entity.Role;
import com.backend.vinbook.entity.User;
import com.backend.vinbook.repository.ProfileRepository;
import com.backend.vinbook.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.backend.vinbook.entity.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@SpringBootApplication
public class VinbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(VinbookApplication.class, args);
	}

    @Bean
    public CommandLineRunner initAdminAccount(UserRepository userRepository,
                                              ProfileRepository profileRepository,
                                              PasswordEncoder passwordEncoder) {
        return args -> {
            // Kiểm tra xem admin đã tồn tại chưa
            if (!userRepository.existsByUsername("admin")) {

                String adminUsername = "admin";
                String adminEmail = "admin@vinbook.com";
                String rawPassword = "Admin@123";
                // 1. Tạo Profile cho Admin
                Profile profile = Profile.builder()
                        .username(adminUsername)
                        .email(adminEmail)
                        .fullName("Nguyễn Đông")
                        .gender("Male")
                        .birthDate(LocalDate.now())
                        .build();
                profileRepository.save(profile);

                // 2. Tạo User Admin
                User admin = new User();
                admin.setUsername(adminUsername);
                admin.setEmail(adminEmail);
                admin.setPassword(passwordEncoder.encode(rawPassword)); // Mã hóa mật khẩu
                admin.setFullName("Nguyễn Đông");
                admin.setGender("Male");
                admin.setBirthDate(LocalDate.now());
                admin.setRole(Role.ADMIN);
                admin.setFirstLogin(false); // Admin không cần đổi pass lần đầu

                userRepository.save(admin);

                System.out.println(">>> Đã tạo tài khoản ADMIN mặc định: admin / " + rawPassword);
            }
        };
    }

}
