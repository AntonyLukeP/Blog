package com.luke.blog.domain.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class CategoryDto {
    private UUID Id;
    private String Name;
    private Long postCount;
}
