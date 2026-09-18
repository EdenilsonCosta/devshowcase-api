package com.devshowcase.dto;

public record ProfileResponse(
    Long id,
    String name,
    String bio,
    String githubUrl,
    String linkedinUrl
) {
}