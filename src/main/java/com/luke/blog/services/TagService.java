package com.luke.blog.services;

import com.luke.blog.domain.entity.Tag;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface TagService {
    List<Tag> getTags();
    List<Tag> createTags(Set<String> tagNames);
    void deleteTag(UUID tagid);
    Tag getTagById(UUID tagid);
}
