package com.devshowcase.controller;

import com.devshowcase.dto.TechnologyRequest;
import com.devshowcase.dto.TechnologyResponse;
import com.devshowcase.service.TechnologyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/technologies")
public class TechnologyController {

    private final TechnologyService technologyService;

    public TechnologyController(TechnologyService technologyService) {
        this.technologyService = technologyService;
    }

    @PostMapping
    public ResponseEntity<TechnologyResponse> create(
        @Valid @RequestBody TechnologyRequest request
    ) {
        TechnologyResponse response = technologyService.create(request);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(response);
    }

    @GetMapping
    public ResponseEntity<List<TechnologyResponse>> findAll() {
        return ResponseEntity.ok(technologyService.findAll());
    }
}