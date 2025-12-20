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



}
