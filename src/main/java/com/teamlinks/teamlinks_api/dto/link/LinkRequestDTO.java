package com.teamlinks.teamlinks_api.dto.link;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;

public record LinkRequestDTO(
    @NotBlank(message = "URL é obrigatória") String url,
    @NotBlank(message = "Nome é obrigatório") String name,
    String description,
    Set<String> tagNames
) {}
