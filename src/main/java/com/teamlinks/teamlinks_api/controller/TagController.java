package com.teamlinks.teamlinks_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import com.teamlinks.teamlinks_api.dto.tag.TagRequestDTO;
import com.teamlinks.teamlinks_api.dto.tag.TagResponseDTO;
import com.teamlinks.teamlinks_api.service.tag.TagService;
import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping
    public ResponseEntity<TagResponseDTO> create(@Valid @RequestBody TagRequestDTO tagRequestDTO) {
        TagResponseDTO created = tagService.create(tagRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{name}")
    public ResponseEntity<TagResponseDTO> findByName(@PathVariable String name) {
        TagResponseDTO tag = tagService.findByName(name);
        return ResponseEntity.ok(tag);
    }

    @GetMapping
    public ResponseEntity<List<TagResponseDTO>> findAll() {
        List<TagResponseDTO> tags = tagService.findAll();
        return ResponseEntity.ok(tags);
    }

    @PutMapping("/{name}")
    public ResponseEntity<TagResponseDTO> update(
            @PathVariable String name,
            @Valid @RequestBody TagRequestDTO tagRequestDTO) {
        
        TagResponseDTO updated = tagService.update(name, tagRequestDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> delete(@PathVariable String name) {
        tagService.delete(name);
        return ResponseEntity.noContent().build();
    }
}
