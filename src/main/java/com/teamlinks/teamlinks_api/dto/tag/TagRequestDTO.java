package com.teamlinks.teamlinks_api.dto.tag;

import jakarta.validation.constraints.NotBlank;

public record TagRequestDTO(@NotBlank(message = "Nome da tag é obrigatório") String name) {

}