package com.luke.blog.domain.controllers;

import com.luke.blog.domain.dtos.CategoryDto;
import com.luke.blog.domain.entity.Category;
import com.luke.blog.domain.mappers.CategoryMapper;
import com.luke.blog.domain.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> listCategories() {
            List<CategoryDto> categories = categoryService.listCategories().stream()
                    .map(category -> categoryMapper.toDto(category))
                    .toList();
            return ResponseEntity.ok(categories);
    }

}
