package com.devshowcase.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

import java.util.Set;

public record ProjectRequest(

    @NotBlank(message = "O título do projeto é obrigatório")
    String title,

    @NotBlank(message = "A descrição do projeto é obrigatória")
    String description,

    @URL(message = "A URL do repositório deve ser válida")
    String repositoryUrl,

    @URL(message = "A URL da demonstração deve ser válida")
    String demoUrl,

    @NotNull(message = "O perfil é obrigatório")
    Long profileId,

    Set<Long> technologyIds

) {
}