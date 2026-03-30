package com.teamlinks.teamlinks_api.service.project;

import com.teamlinks.teamlinks_api.dto.project.ProjectRequestDTO;
import com.teamlinks.teamlinks_api.dto.project.ProjectResponseDTO;

public interface ProjectService {

    public ProjectResponseDTO create(ProjectRequestDTO projectRequestDTO);

    public ProjectResponseDTO findByName(String name);
    
    public void delete(String name);

    public ProjectResponseDTO update(String name, ProjectRequestDTO projectRequestDTO);

}
