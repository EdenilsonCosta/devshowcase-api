package com.devshowcase.dto;

import jakarta.validation.constraints.NotBlank;

public record TechnologyRequest(

    @NotBlank(message = "O nome da tecnologia é obrigatório")
    String name

) {
}