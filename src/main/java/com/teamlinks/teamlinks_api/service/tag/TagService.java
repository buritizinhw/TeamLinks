package com.teamlinks.teamlinks_api.service.tag;

import com.teamlinks.teamlinks_api.dto.tag.TagRequestDTO;
import com.teamlinks.teamlinks_api.dto.tag.TagResponseDTO;
import java.util.List;

public interface TagService {

    public TagResponseDTO create(TagRequestDTO tagRequestDTO);

    public TagResponseDTO findByName(String name);

    public List<TagResponseDTO> findAll();

    public TagResponseDTO update(String oldName, TagRequestDTO tagRequestDTO);

    public void delete(String name);

}
