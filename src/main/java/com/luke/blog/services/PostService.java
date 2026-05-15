package com.luke.blog.services;

import com.luke.blog.domain.CreatePostRequest;
import com.luke.blog.domain.UpdatePostRequest;
import com.luke.blog.domain.entity.Post;
import com.luke.blog.domain.entity.User;

import java.util.List;
import java.util.UUID;

public interface PostService {
    Post getPost(UUID id);
    List<Post> getAllPosts(UUID categoryId, UUID tagId);
    List<Post> getDraftPosts(User user);
    Post createPost(User user, CreatePostRequest createPostRequest);
    Post updatePost(UUID id, UpdatePostRequest updatePostRequest);
}
