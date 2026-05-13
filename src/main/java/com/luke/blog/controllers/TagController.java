package com.luke.blog.controllers;

import com.luke.blog.domain.dtos.CreateTagsRequest;
import com.luke.blog.domain.dtos.TagDto;
import com.luke.blog.domain.entity.Tag;
import com.luke.blog.mappers.TagMapper;
import com.luke.blog.repositories.TagRepository;
import com.luke.blog.services.TagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/v1/tags")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;
    private final TagMapper tagMapper;
    private final TagRepository tagRepository;

    @GetMapping
    public ResponseEntity<?> getTags() {
        List<Tag> tags = tagService.getTags();
        List<TagDto> tagRespons = tags.stream().map(tagMapper::toTagResponse).toList();
        return ResponseEntity.ok(tagRespons);
    }

    @PostMapping
    public ResponseEntity<List<TagDto>> createTag(@Valid @RequestBody CreateTagsRequest  createTagsRequest) {
            List<Tag>  savedTags = tagService.createTags(createTagsRequest.getNames());
            List<TagDto> createdTagRespons = savedTags.stream().map(tagMapper::toTagResponse).collect(Collectors.toList());
            return ResponseEntity.ok(createdTagRespons);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable UUID id) {
        tagService.deleteTag(id);
        return ResponseEntity.noContent().build();
    }
}
