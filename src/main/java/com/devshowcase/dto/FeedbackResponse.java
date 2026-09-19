package com.devshowcase.dto;

public record FeedbackResponse(
    Long id,
    Integer rating,
    String comment,
    Long projectId,
    Double averageRating
) {
}