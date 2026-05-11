package com.luke.blog.services.impl;

import com.luke.blog.domain.entity.Tag;
import com.luke.blog.repositories.TagRepository;
import com.luke.blog.services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Override
    public List<Tag> getTags() {
        return tagRepository.findAllWithPostCount();
    }
}
