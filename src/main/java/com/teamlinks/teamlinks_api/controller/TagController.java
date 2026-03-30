package com.teamlinks.teamlinks_api.controller;

import com.teamlinks.teamlinks_api.dto.tag.TagRequestDTO;
import com.teamlinks.teamlinks_api.dto.tag.TagResponseDTO;
import com.teamlinks.teamlinks_api.service.tag.TagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @PostMapping
    public ResponseEntity<TagResponseDTO> create(@Valid @RequestBody TagRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tagService.create(dto));
    }

    @GetMapping
    public ResponseEntity<Page<TagResponseDTO>> findAll(@PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(tagService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(tagService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TagResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody TagRequestDTO dto) {
        return ResponseEntity.ok(tagService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tagService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
