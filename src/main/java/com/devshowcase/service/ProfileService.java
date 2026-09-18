package com.devshowcase.service;

import com.devshowcase.dto.ProfileRequest;
import com.devshowcase.dto.ProfileResponse;
import com.devshowcase.model.Profile;
import com.devshowcase.repository.ProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public ProfileResponse create(ProfileRequest request) {
        Profile profile = new Profile(
            request.name(),
            request.bio(),
            request.githubUrl(),
            request.linkedinUrl()
        );

        Profile savedProfile = profileRepository.save(profile);

        return toResponse(savedProfile);
    }

    public ProfileResponse findById(Long id) {
        Profile profile = profileRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Perfil não encontrado"));

        return toResponse(profile);
    }

    private ProfileResponse toResponse(Profile profile) {
        return new ProfileResponse(
            profile.getId(),
            profile.getName(),
            profile.getBio(),
            profile.getGithubUrl(),
            profile.getLinkedinUrl()
        );
    }
}