package com.teamlinks.teamlinks_api.service.project;

import com.teamlinks.teamlinks_api.dto.project.ProjectRequestDTO;
import com.teamlinks.teamlinks_api.dto.project.ProjectResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProjectService {

    ProjectResponseDTO create(ProjectRequestDTO dto);

    ProjectResponseDTO findById(Long id);

    Page<ProjectResponseDTO> findAll(Pageable pageable);

    ProjectResponseDTO update(Long id, ProjectRequestDTO dto);

    void delete(Long id);
}
