package com.luke.blog.services.impl;

import com.luke.blog.domain.CreatePostRequest;
import com.luke.blog.domain.PostStatus;
import com.luke.blog.domain.entity.Category;
import com.luke.blog.domain.entity.Post;
import com.luke.blog.domain.entity.Tag;
import com.luke.blog.domain.entity.User;
import com.luke.blog.repositories.PostRepository;
import com.luke.blog.services.CategoryService;
import com.luke.blog.services.PostService;
import com.luke.blog.services.TagService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final CategoryService categoryService;
    private final TagService tagService;

    private static final int WORDS_PER_MINUTE = 200;

    @Override
    @Transactional
    public List<Post> getAllPosts(UUID categoryId, UUID tagId) {
        if(categoryId != null && tagId != null) {
            Category category = categoryService.getCategoryById(categoryId);
            Tag  tag = tagService.getTagById(tagId);
            return postRepository.findByStatusAndCategoryAndTagsContaining(
                    PostStatus.PUBLISHED, category, tag
            );
        }
        if(categoryId != null){
            Category category = categoryService.getCategoryById(categoryId);
            return postRepository.findByStatusAndCategory(
                 PostStatus.PUBLISHED, category);
        }
        if(tagId != null){
            Tag tag = tagService.getTagById(tagId);
            return postRepository.findByStatusAndTagsContaining(
                    PostStatus.PUBLISHED,tag
            );

        }
            return postRepository.findByStatus(PostStatus.PUBLISHED);
     }

    @Override
    public List<Post> getDraftPosts(User user) {
        return postRepository.findAllByAuthorAndStatus(user, PostStatus.DRAFT);
    }

    @Override
    public Post createPost(User user, CreatePostRequest createPostRequest) {
        Post newPost = new Post();
        newPost.setTitle(createPostRequest.getTitle());
        newPost.setContent(createPostRequest.getContent());
        newPost.setStatus(createPostRequest.getStatus());
        newPost.setAuthor(user);
        newPost.setReadingTime(calculateWordsPerMinute(createPostRequest.getContent()));

        Category category = categoryService.getCategoryById(createPostRequest.getCategoryId());
        newPost.setCategory(category);

        Set<UUID> tagIds = createPostRequest.getTags();
        List<Tag> tags = tagService.getTagsByIds(tagIds);
        newPost.getTags().addAll(tags);

        return postRepository.save(newPost);

    }

    private int calculateWordsPerMinute(String content) {
        if(content != null || content.isEmpty()  ) {
            return 0;
        }
        int wordCount = content.trim().split("\\s+").length;
        return (int)Math.ceil((double) wordCount / WORDS_PER_MINUTE);
    }
}
