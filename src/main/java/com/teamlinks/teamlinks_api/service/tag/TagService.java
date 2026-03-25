package com.teamlinks.teamlinks_api.service.tag;

import org.springframework.stereotype.Service;

import com.teamlinks.teamlinks_api.dto.tag.TagRequestDTO;
import com.teamlinks.teamlinks_api.dto.tag.TagResponseDTO;
import com.teamlinks.teamlinks_api.repository.TagRepository;
import com.teamlinks.teamlinks_api.repository.LinkRepository;
import com.teamlinks.teamlinks_api.entity.Tag;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService {

    private final TagRepository tagRepository;
    private final LinkRepository linkRepository;

    public TagService(TagRepository tagRepository, LinkRepository linkRepository) {
        this.tagRepository = tagRepository;
        this.linkRepository = linkRepository;
    }

    public TagResponseDTO create(TagRequestDTO tagRequestDTO) {
        var tag = new Tag();
        tag.setName(tagRequestDTO.name());
        if(tagRepository.existsByName(tag.getName())) {
            throw new IllegalArgumentException("Tag com nome '" + tag.getName() + "' já existe.");
        }
        var savedTag = tagRepository.save(tag);
        return TagResponseDTO.fromEntity(savedTag);
    }

    public TagResponseDTO findByName(String name) {
        Tag tag = tagRepository.findByName(name)
            .orElseThrow(() -> new IllegalArgumentException("Tag '" + name + "' não encontrada."));
        return TagResponseDTO.fromEntity(tag);
    }

    public List<TagResponseDTO> findAll() {
        return tagRepository.findAll().stream()
            .map(TagResponseDTO::fromEntity)
            .collect(Collectors.toList());
    }

    public TagResponseDTO update(String oldName, TagRequestDTO tagRequestDTO) {
        Tag tag = tagRepository.findByName(oldName)
            .orElseThrow(() -> new IllegalArgumentException("Tag '" + oldName + "' não encontrada."));
        
        if (!tag.getName().equals(tagRequestDTO.name()) && tagRepository.existsByName(tagRequestDTO.name())) {
            throw new IllegalArgumentException("Tag com nome '" + tagRequestDTO.name() + "' já existe.");
        }
        
        tag.setName(tagRequestDTO.name());
        Tag updatedTag = tagRepository.save(tag);
        return TagResponseDTO.fromEntity(updatedTag);
    }

    public void delete(String name) {
        Tag tag = tagRepository.findByName(name)
            .orElseThrow(() -> new IllegalArgumentException("Tag '" + name + "' não encontrada."));
        
        long linksCount = linkRepository.countByTagsId(tag.getId());
        if (linksCount > 0) {
            throw new IllegalArgumentException(
                "Não é possível deletar a tag '" + tag.getName() + "'. " +
                "Ela está associada a " + linksCount + " link(s). " +
                "Remova a tag dos links antes de deletá-la."
            );
        }
        
        tagRepository.deleteById(tag.getId());
    }

}
