package com.teamlinks.teamlinks_api.dto.tag;

import com.teamlinks.teamlinks_api.entity.Tag;

public record TagResponseDTO(Long id, String name) {

    public static TagResponseDTO fromEntity(Tag tag) { return new TagResponseDTO(tag.getId(), tag.getName());}
}