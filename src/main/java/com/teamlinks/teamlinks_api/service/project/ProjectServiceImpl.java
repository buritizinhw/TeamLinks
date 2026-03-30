package com.teamlinks.teamlinks_api.service.project;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.teamlinks.teamlinks_api.dto.project.ProjectRequestDTO;
import com.teamlinks.teamlinks_api.dto.project.ProjectResponseDTO;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    
    @Override
    public ProjectResponseDTO create(ProjectRequestDTO projectRequestDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public ProjectResponseDTO findByName(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByName'");
    }

    @Override
    public void delete(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public ProjectResponseDTO update(String name, ProjectRequestDTO projectRequestDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
    

}
