package com.backend.vinbook.repository;

import com.backend.vinbook.entity.Profile;
import com.backend.vinbook.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByUsername(String username);
    Optional<Profile> findByEmail(String email);

    boolean existsByUsername(String username);


}
