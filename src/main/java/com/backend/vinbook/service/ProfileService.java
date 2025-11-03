package com.backend.vinbook.service;

import com.backend.vinbook.dto.ProfileDTO;
import com.backend.vinbook.dto.UpdateProfileDTO;

public interface ProfileService {
    void updateProfile(UpdateProfileDTO updateProfileDTO);
    ProfileDTO getProfile( );
}
