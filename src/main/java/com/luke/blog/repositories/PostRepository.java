package com.luke.blog.repositories;

import com.luke.blog.domain.PostStatus;
import com.luke.blog.domain.entity.Category;
import com.luke.blog.domain.entity.Post;
import com.luke.blog.domain.entity.Tag;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
    List<Post> findByStatusAndCategoryAndTagsContaining(PostStatus status, Category category, Tag tag);
    List<Post> findByStatusAndCategory(PostStatus status, Category category);
    List<Post> findByStatusAndTagsContaining(PostStatus status, Tag tag);
    List<Post> findByStatus(PostStatus status);
}
