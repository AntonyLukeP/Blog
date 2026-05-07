package com.luke.blog.services;

import com.luke.blog.domain.dtos.CreateCategoryRequest;
import com.luke.blog.domain.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    List<Category> listCategories();
    Category createCategory(Category category);
}
