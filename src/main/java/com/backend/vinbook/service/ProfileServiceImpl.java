package com.backend.vinbook.service;

import com.backend.vinbook.dto.ProfileDTO;
import com.backend.vinbook.dto.UpdateProfileDTO;
import com.backend.vinbook.entity.Profile;
import com.backend.vinbook.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService{
    private final ProfileRepository profileRepository;
    @Override
    public void updateProfile(UpdateProfileDTO updateProfileDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("Username : {}", username);
        Profile profile = profileRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Ko tim thay profile"));
        log.info("profile : {}", profile);

        profile.setFullName(updateProfileDTO.getFullName());
        profile.setGender(updateProfileDTO.getGender());
        profile.setBirthDate(updateProfileDTO.getBirthDate());
        profileRepository.save(profile);
    }

    @Override
    public ProfileDTO getProfile() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Profile profile = profileRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Ko tim thay profile"));
        return profile.toDTO();
    }
}
