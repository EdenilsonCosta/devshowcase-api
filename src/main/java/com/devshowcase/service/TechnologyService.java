package com.devshowcase.service;

import com.devshowcase.dto.TechnologyRequest;
import com.devshowcase.dto.TechnologyResponse;
import com.devshowcase.model.Technology;
import com.devshowcase.repository.TechnologyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnologyService {

    private final TechnologyRepository technologyRepository;

    public TechnologyService(TechnologyRepository technologyRepository) {
        this.technologyRepository = technologyRepository;
    }

    public TechnologyResponse create(TechnologyRequest request) {
        Technology technology = new Technology(request.name());

        Technology savedTechnology = technologyRepository.save(technology);

        return toResponse(savedTechnology);
    }

    public List<TechnologyResponse> findAll() {
        return technologyRepository.findAll()
            .stream()
            .map(this::toResponse)
            .toList();
    }

    private TechnologyResponse toResponse(Technology technology) {
        return new TechnologyResponse(
            technology.getId(),
            technology.getName()
        );
    }
}