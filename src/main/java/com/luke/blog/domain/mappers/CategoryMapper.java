package com.luke.blog.domain.mappers;

import com.luke.blog.domain.PostStatus;
import com.luke.blog.domain.dtos.CategoryDto;
import com.luke.blog.domain.entity.Category;
import com.luke.blog.domain.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE )
public interface CategoryMapper {

    @Mapping(target = "postCount", source = "posts", qualifiedByName = "calculatePostCount")
    CategoryDto toDto(Category category);

    @Named("calculatedPostCount")
    default long calculatePostCount(List<Post>posts) {
        if(posts == null || posts.isEmpty()) {
            return 0;
        }
        return posts.stream()
                .filter(post -> PostStatus.PUBLISHED.equals(post.getStatus()))
                .count();

    }

}
