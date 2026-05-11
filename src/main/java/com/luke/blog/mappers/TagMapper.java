package com.luke.blog.mappers;

import com.luke.blog.domain.PostStatus;
import com.luke.blog.domain.dtos.TagResponse;
import com.luke.blog.domain.entity.Post;
import com.luke.blog.domain.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagMapper {

    @Mapping(target = "postCount", source = "posts", qualifiedByName = "calculatePostCount")
    TagResponse toTagResponse(Tag tag);

    @Named("calculatePostCount")
    default Integer calculatePostCount(List<Post>  posts) {
        if(posts == null || posts.size() == 0) {
            return 0;
        }
        return (int)posts.stream()
                .filter(post -> PostStatus.PUBLISHED.equals(post.getStatus()))
                .count();
    }
}
