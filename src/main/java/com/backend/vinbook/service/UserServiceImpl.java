        package com.backend.vinbook.service;

        import com.backend.vinbook.dto.UserDTO;
        import com.backend.vinbook.entity.User;
        import com.backend.vinbook.repository.UserRepository;
        import lombok.RequiredArgsConstructor;
        import org.springframework.security.crypto.password.PasswordEncoder;
        import org.springframework.stereotype.Service;

        import java.util.Optional;

        @RequiredArgsConstructor
        @Service
        public class UserServiceImpl implements UserService{
            private final UserRepository userRepository;
            private final PasswordEncoder passwordEncoder;

            @Override
            public void registerUser(UserDTO userDTO) {
                Optional<User> opt = userRepository.findByUsername(userDTO.getUsername());
                if (opt.isPresent()){
                    throw new RuntimeException("User da ton tai");
                }
                userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
                userRepository.save(userDTO.toEntity());
            }

            @Override
            public UserDTO getUser(String username) {

                return userRepository.findByUsername(username).orElseThrow(
                        () -> new RuntimeException("username khong ton tai")).toDTO();
            }
        }
