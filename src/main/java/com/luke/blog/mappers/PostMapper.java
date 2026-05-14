package com.luke.blog.mappers;

import com.luke.blog.domain.CreatePostRequest;
import com.luke.blog.domain.dtos.CreatePostRequestDto;
import com.luke.blog.domain.dtos.PostDto;
import com.luke.blog.domain.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper   {

    @Mapping(target = "author", source = "author")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "tags", source = "tags")
    PostDto toDto(Post post);

    CreatePostRequest toCreatePostrequest(CreatePostRequestDto dto);
}
