package com.teamlinks.teamlinks_api.dto.project;
import java.time.Instant;

import com.teamlinks.teamlinks_api.entity.Project;

public record ProjectResponseDTO(Long id,String name,Instant createdAt){        
    
    public static ProjectResponseDTO fromEntity(Project project) {
        return new ProjectResponseDTO(project.getId(), project.getName(), project.getCreatedAt());}
}
