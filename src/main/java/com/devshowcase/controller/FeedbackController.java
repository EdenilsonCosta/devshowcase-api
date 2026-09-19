package com.devshowcase.controller;

import com.devshowcase.dto.FeedbackRequest;
import com.devshowcase.dto.FeedbackResponse;
import com.devshowcase.service.FeedbackService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping("/{id}/feedbacks")
    public ResponseEntity<FeedbackResponse> create(
        @PathVariable Long id,
        @Valid @RequestBody FeedbackRequest request
    ) {
        FeedbackResponse response = feedbackService.create(id, request);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(response);
    }
}