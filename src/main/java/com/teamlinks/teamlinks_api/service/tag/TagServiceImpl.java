package com.teamlinks.teamlinks_api.service.tag;

import com.teamlinks.teamlinks_api.dto.tag.TagRequestDTO;
import com.teamlinks.teamlinks_api.dto.tag.TagResponseDTO;
import com.teamlinks.teamlinks_api.entity.Tag;
import com.teamlinks.teamlinks_api.repository.LinkRepository;
import com.teamlinks.teamlinks_api.repository.TagRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final LinkRepository linkRepository;

    @Override
    @Transactional
    public TagResponseDTO create(TagRequestDTO dto) {
        if (tagRepository.existsByName(dto.name())) {
            throw new IllegalArgumentException("Tag com nome '" + dto.name() + "' já existe.");
        }

        var tag = new Tag();
        tag.setName(dto.name());

        return TagResponseDTO.fromEntity(tagRepository.save(tag));
    }

    @Override
    public TagResponseDTO findById(Long id) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tag com ID " + id + " não encontrada."));
        return TagResponseDTO.fromEntity(tag);
    }

    @Override
    public Page<TagResponseDTO> findAll(Pageable pageable) {
        return tagRepository.findAll(pageable)
                .map(TagResponseDTO::fromEntity);
    }

    @Override
    @Transactional
    public TagResponseDTO update(Long id, TagRequestDTO dto) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tag com ID " + id + " não encontrada."));

        if (!tag.getName().equals(dto.name()) && tagRepository.existsByName(dto.name())) {
            throw new IllegalArgumentException("Tag com nome '" + dto.name() + "' já existe.");
        }

        tag.setName(dto.name());

        return TagResponseDTO.fromEntity(tagRepository.save(tag));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tag com ID " + id + " não encontrada."));

        linkRepository.findAllByTagsContaining(tag)
                .forEach(link -> link.getTags().remove(tag));

        tagRepository.delete(tag);
    }
}
