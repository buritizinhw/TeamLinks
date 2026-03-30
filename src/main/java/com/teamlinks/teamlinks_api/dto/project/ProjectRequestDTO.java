package com.teamlinks.teamlinks_api.dto.project;

import jakarta.validation.constraints.NotBlank;

public record ProjectRequestDTO(
    @NotBlank(message = "Nome do projeto é obrigatório") String name,
    String description
) {}
