package com.teamlinks.teamlinks_api.service.link;
import com.teamlinks.teamlinks_api.dto.link.LinkRequestDTO;
import com.teamlinks.teamlinks_api.dto.link.LinkResponseDTO;
import com.teamlinks.teamlinks_api.entity.Link;
import com.teamlinks.teamlinks_api.entity.Project;
import com.teamlinks.teamlinks_api.entity.Tag;
import com.teamlinks.teamlinks_api.repository.LinkRepository;
import com.teamlinks.teamlinks_api.repository.ProjectRepository;
import com.teamlinks.teamlinks_api.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class LinkServiceImpl implements LinkService {

    private final LinkRepository linkRepository;
    private final ProjectRepository projectRepository;
    private final TagRepository tagRepository;

    @Override
    public LinkResponseDTO create(LinkRequestDTO linkRequestDTO, Long projectId) {
        if (linkRepository.existsByUrl(linkRequestDTO.url())) {
            throw new IllegalArgumentException("Já existe um link com a URL '" + linkRequestDTO.url() + "'.");
        }

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Projeto com ID " + projectId + " não encontrado."));

        Link link = new Link();
        link.setUrl(linkRequestDTO.url());
        link.setName(linkRequestDTO.name());
        link.setProject(project);
        link.setClicks(0L);

        Set<Tag> tags = new HashSet<>();
        if (linkRequestDTO.tagNames() != null && !linkRequestDTO.tagNames().isEmpty()) {
            for (String tagName : linkRequestDTO.tagNames()) {
                Tag tag = tagRepository.findByName(tagName)
                        .orElseThrow(() -> new IllegalArgumentException("Tag '" + tagName + "' não encontrada. Crie a tag primeiro."));
                tags.add(tag);
            }
        }
        link.setTags(tags);

        Link savedLink = linkRepository.save(link);
        return LinkResponseDTO.fromEntity(savedLink);
    }

    public LinkResponseDTO findByUrl(String url) {
        Link link = linkRepository.findByUrl(url)
                .orElseThrow(() -> new IllegalArgumentException("Link com URL '" + url + "' não encontrado."));
        return LinkResponseDTO.fromEntity(link);
    }

    public void delete(String url) {
        Link link = linkRepository.findByUrl(url)
                .orElseThrow(() -> new IllegalArgumentException("Link com URL '" + url + "' não encontrado."));
        linkRepository.deleteById(link.getId());
    }

    public LinkResponseDTO updateTags(String url, Set<String> tagNames) {
        Link link = linkRepository.findByUrl(url)
                .orElseThrow(() -> new IllegalArgumentException("Link com URL '" + url + "' não encontrado."));

        Set<Tag> tags = new HashSet<>();
        if (tagNames != null && !tagNames.isEmpty()) {
            for (String tagName : tagNames) {
                Tag tag = tagRepository.findByName(tagName)
                        .orElseThrow(() -> new IllegalArgumentException("Tag '" + tagName + "' não encontrada."));
                tags.add(tag);
            }
        }

        link.setTags(tags);
        Link updatedLink = linkRepository.save(link);
        return LinkResponseDTO.fromEntity(updatedLink);
    }

}
