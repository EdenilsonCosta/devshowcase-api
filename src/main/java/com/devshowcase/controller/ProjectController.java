package com.devshowcase.controller;

import com.devshowcase.dto.ProjectRequest;
import com.devshowcase.dto.ProjectResponse;
import com.devshowcase.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<ProjectResponse> create(
        @Valid @RequestBody ProjectRequest request
    ) {
        ProjectResponse response = projectService.create(request);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(response);
    }

    @GetMapping
    public ResponseEntity<Page<ProjectResponse>> findAll(
        @RequestParam(required = false) String technology,
        Pageable pageable
    ) {
        return ResponseEntity.ok(
            projectService.findAll(technology, pageable)
        );
    }

    @PutMapping("/{id}/upvote")
    public ResponseEntity<ProjectResponse> upvote(
        @PathVariable Long id
    ) {
        return ResponseEntity.ok(projectService.upvote(id));
    }
}