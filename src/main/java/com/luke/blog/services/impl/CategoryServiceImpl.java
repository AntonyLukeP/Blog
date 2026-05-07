package com.luke.blog.services.impl;

import com.luke.blog.domain.entity.Category;
import com.luke.blog.repositories.CategoryRespository;
import com.luke.blog.services.CategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRespository categoryRespository;

    @Override
    public List<Category>  listCategories() {
        return categoryRespository.findAllWithPostCount();
    }

    @Override
    @Transactional
    public Category createCategory(Category category) {
        String categoryName = category.getName();
        if(categoryRespository.existsByNameIgnoreCase(categoryName)){
            throw new IllegalArgumentException("Category already exists with name: " + categoryName);

        }
        return categoryRespository.save(category);
    }
}
