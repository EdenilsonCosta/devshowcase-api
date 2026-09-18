package com.devshowcase.controller;

import com.devshowcase.dto.ProfileRequest;
import com.devshowcase.dto.ProfileResponse;
import com.devshowcase.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping
    public ResponseEntity<ProfileResponse> create(
        @Valid @RequestBody ProfileRequest request
    ) {
        ProfileResponse response = profileService.create(request);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponse> findById(
        @PathVariable Long id
    ) {
        ProfileResponse response = profileService.findById(id);

        return ResponseEntity.ok(response);
    }
}