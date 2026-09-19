package com.devshowcase.service;

import com.devshowcase.dto.FeedbackRequest;
import com.devshowcase.dto.FeedbackResponse;
import com.devshowcase.exception.ResourceNotFoundException;
import com.devshowcase.model.Feedback;
import com.devshowcase.model.Project;
import com.devshowcase.repository.FeedbackRepository;
import com.devshowcase.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final ProjectRepository projectRepository;

    public FeedbackService(
        FeedbackRepository feedbackRepository,
        ProjectRepository projectRepository
    ) {
        this.feedbackRepository = feedbackRepository;
        this.projectRepository = projectRepository;
    }

    public FeedbackResponse create(Long projectId, FeedbackRequest request) {

        Project project = projectRepository.findById(projectId)
            .orElseThrow(() ->
                new ResourceNotFoundException("Projeto não encontrado")
            );

        Feedback feedback = new Feedback();
        feedback.setRating(request.rating());
        feedback.setComment(request.comment());
        feedback.setProject(project);

        Feedback savedFeedback = feedbackRepository.save(feedback);

        List<Feedback> feedbacks =
            feedbackRepository.findByProjectId(projectId);

        double averageRating = feedbacks.stream()
            .mapToInt(Feedback::getRating)
            .average()
            .orElse(0.0);

        project.setAverageRating(averageRating);
        projectRepository.save(project);

        return new FeedbackResponse(
            savedFeedback.getId(),
            savedFeedback.getRating(),
            savedFeedback.getComment(),
            project.getId(),
            project.getAverageRating()
        );
    }
}