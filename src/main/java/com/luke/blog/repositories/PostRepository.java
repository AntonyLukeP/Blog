package com.luke.blog.repositories;

import com.luke.blog.domain.PostStatus;
import com.luke.blog.domain.entity.Category;
import com.luke.blog.domain.entity.Post;
import com.luke.blog.domain.entity.Tag;
import com.luke.blog.domain.entity.User;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
    List<Post> findByStatusAndCategoryAndTagsContaining(PostStatus status, Category category, Tag tag);
    List<Post> findByStatusAndCategory(PostStatus status, Category category);
    List<Post> findByStatusAndTagsContaining(PostStatus status, Tag tag);
    List<Post> findByStatus(PostStatus status);


    @Query("""
    SELECT DISTINCT p
    FROM Post p
    LEFT JOIN FETCH p.author
    LEFT JOIN FETCH p.category
    LEFT JOIN FETCH p.tags
    WHERE p.author = :author
    AND p.status = :status
    """)
    List<Post> findAllByAuthorAndStatus(
            @Param("author") User author,
            @Param("status") PostStatus status
    );
}
