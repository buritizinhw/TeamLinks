package com.teamlinks.teamlinks_api.service.link;

import com.teamlinks.teamlinks_api.dto.link.LinkRequestDTO;
import com.teamlinks.teamlinks_api.dto.link.LinkResponseDTO;
import java.util.Set;

public interface LinkService{

    public LinkResponseDTO createLink(LinkRequestDTO linkRequestDTO, Long projectId);

    public LinkResponseDTO findByUrl(String url);

    public void deleteLink(String url);

    public LinkResponseDTO updateTags(String url, Set<String> tagNames);

}
