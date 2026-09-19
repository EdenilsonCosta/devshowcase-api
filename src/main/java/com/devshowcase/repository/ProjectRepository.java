package com.devshowcase.repository;

import com.devshowcase.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    Page<Project> findDistinctByTechnologiesNameIgnoreCase(
        String technology,
        Pageable pageable
    );
}