package com.teamlinks.teamlinks_api.dto.link;

import com.teamlinks.teamlinks_api.entity.Link;
import com.teamlinks.teamlinks_api.entity.Tag;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

public record LinkResponseDTO(
    Long id,
    String url,
    String name,
    String description,
    Long projectId,
    String projectName,
    Set<String> tagNames,
    Instant createdAt,
    Instant updatedAt
) {
    public static LinkResponseDTO fromEntity(Link link) {
        return new LinkResponseDTO(
            link.getId(),
            link.getUrl(),
            link.getName(),
            link.getDescription(),
            link.getProject().getId(),
            link.getProject().getName(),
            link.getTags().stream()
                .map(Tag::getName)
                .collect(Collectors.toSet()),
            link.getCreatedAt(),
            link.getUpdatedAt()
        );
    }
}
