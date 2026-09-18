package com.devshowcase.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public record ProfileRequest(

    @NotBlank(message = "O nome é obrigatório")
    String name,

    @NotBlank(message = "A biografia é obrigatória")
    String bio,

    @URL(message = "A URL do GitHub deve ser válida")
    String githubUrl,

    @URL(message = "A URL do LinkedIn deve ser válida")
    String linkedinUrl

) {
}