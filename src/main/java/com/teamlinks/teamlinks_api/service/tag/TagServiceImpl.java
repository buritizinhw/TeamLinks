package com.teamlinks.teamlinks_api.service.tag;

import com.teamlinks.teamlinks_api.dto.tag.TagRequestDTO;
import com.teamlinks.teamlinks_api.dto.tag.TagResponseDTO;
import com.teamlinks.teamlinks_api.entity.Tag;
import com.teamlinks.teamlinks_api.repository.LinkRepository;
import com.teamlinks.teamlinks_api.repository.TagRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService{

    private final TagRepository tagRepository;
    private final LinkRepository linkRepository;


    @Override
    public TagResponseDTO create(TagRequestDTO tagRequestDTO) {
        var tag = new Tag();
        tag.setName(tagRequestDTO.name());
        if(tagRepository.existsByName(tag.getName())) {
            throw new IllegalArgumentException("Tag com nome '" + tag.getName() + "' já existe.");
        }
        var savedTag = tagRepository.save(tag);
        return TagResponseDTO.fromEntity(savedTag);
    }

    @Override
    public TagResponseDTO findByName(String name) {

        return null;
    }

    @Override
    public List<TagResponseDTO> findAll() {
        return List.of();
    }

    @Override
    public TagResponseDTO update(String oldName, TagRequestDTO tagRequestDTO) {
        return null;
    }

    @Override
    public void delete(String name) {


    }
}
