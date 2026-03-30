package com.teamlinks.teamlinks_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.fasterxml.jackson.databind.JsonNode;

import com.teamlinks.teamlinks_api.dto.link.LinkRequestDTO;
import com.teamlinks.teamlinks_api.dto.link.LinkResponseDTO;
import com.teamlinks.teamlinks_api.service.link.LinkService;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/links")
public class LinkController {

    private final LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping("/{projectId}")
    public ResponseEntity<LinkResponseDTO> create(
            @PathVariable Long projectId,
            @Valid @RequestBody LinkRequestDTO linkRequestDTO) {
        
        LinkResponseDTO created = linkService.create(linkRequestDTO, projectId);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{url}")
    public ResponseEntity<LinkResponseDTO> findByUrl(@PathVariable String url) {
        LinkResponseDTO link = linkService.findByUrl(url);
        return ResponseEntity.ok(link);
    }

    @DeleteMapping("/{url}")
    public ResponseEntity<Void> delete(@PathVariable String url) {
        linkService.delete(url);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{url}/tags")
    public ResponseEntity<LinkResponseDTO> updateTags(
            @PathVariable String url,
            @RequestBody JsonNode request) {
        
        Set<String> tagNames = new HashSet<>();
        if (request.has("tagNames") && request.get("tagNames").isArray()) {
            request.get("tagNames").forEach(node -> tagNames.add(node.asText()));
        }
        
        LinkResponseDTO updated = linkService.updateTags(url, tagNames);
        return ResponseEntity.ok(updated);
    }
}
