package com.luke.blog.services;

import com.luke.blog.domain.entity.Tag;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface TagService {
    List<Tag> getTags();
    List<Tag> createTags(Set<String> tagNames);

    @Transactional
    void deleteTag(UUID tagid);
}
