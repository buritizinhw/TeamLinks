package com.teamlinks.teamlinks_api.service.tag;

import com.teamlinks.teamlinks_api.dto.tag.TagRequestDTO;
import com.teamlinks.teamlinks_api.dto.tag.TagResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TagService {

    TagResponseDTO create(TagRequestDTO dto);

    TagResponseDTO findById(Long id);

    Page<TagResponseDTO> findAll(Pageable pageable);

    TagResponseDTO update(Long id, TagRequestDTO dto);

    void delete(Long id);
}
