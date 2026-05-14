package com.luke.blog.domain;

import com.luke.blog.domain.PostStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostRequest {

    private String title;

    private String content;

    private UUID categoryId;

    @Builder.Default
    private Set<UUID> tags = new HashSet<>();

    private PostStatus status;

}
