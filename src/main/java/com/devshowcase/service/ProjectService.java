package com.devshowcase.service;

import com.devshowcase.dto.ProjectRequest;
import com.devshowcase.dto.ProjectResponse;
import com.devshowcase.dto.TechnologyResponse;
import com.devshowcase.model.Profile;
import com.devshowcase.model.Project;
import com.devshowcase.model.Technology;
import com.devshowcase.repository.ProfileRepository;
import com.devshowcase.repository.ProjectRepository;
import com.devshowcase.repository.TechnologyRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProfileRepository profileRepository;
    private final TechnologyRepository technologyRepository;

    public ProjectService(
        ProjectRepository projectRepository,
        ProfileRepository profileRepository,
        TechnologyRepository technologyRepository
    ) {
        this.projectRepository = projectRepository;
        this.profileRepository = profileRepository;
        this.technologyRepository = technologyRepository;
    }

    public ProjectResponse create(ProjectRequest request) {

        Profile profile = profileRepository.findById(request.profileId())
            .orElseThrow(() -> new RuntimeException("Perfil não encontrado"));

        Set<Technology> technologies = new HashSet<>();

        if (request.technologyIds() != null) {
            technologies.addAll(
                technologyRepository.findAllById(request.technologyIds())
            );
        }

        Project project = new Project();

        project.setTitle(request.title());
        project.setDescription(request.description());
        project.setRepositoryUrl(request.repositoryUrl());
        project.setDemoUrl(request.demoUrl());
        project.setProfile(profile);
        project.setTechnologies(technologies);

        Project savedProject = projectRepository.save(project);

        return toResponse(savedProject);
    }

    public List<ProjectResponse> findAll() {
        return projectRepository.findAll()
            .stream()
            .map(this::toResponse)
            .toList();
    }

    private ProjectResponse toResponse(Project project) {

        Set<TechnologyResponse> technologies = project.getTechnologies()
            .stream()
            .map(technology -> new TechnologyResponse(
                technology.getId(),
                technology.getName()
            ))
            .collect(java.util.stream.Collectors.toSet());

        return new ProjectResponse(
            project.getId(),
            project.getTitle(),
            project.getDescription(),
            project.getRepositoryUrl(),
            project.getDemoUrl(),
            project.getProfile().getId(),
            technologies
        );
    }
}