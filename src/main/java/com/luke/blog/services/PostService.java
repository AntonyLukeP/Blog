package com.luke.blog.services;

import com.luke.blog.domain.CreatePostRequest;
import com.luke.blog.domain.entity.Post;
import com.luke.blog.domain.entity.User;

import java.util.List;
import java.util.UUID;

public interface PostService {
    List<Post> getAllPosts(UUID categoryId, UUID tagId);
    List<Post> getDraftPosts(User user);
    Post createPost(User user, CreatePostRequest createPostRequest);
}
