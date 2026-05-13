package com.luke.blog.services.impl;

import com.luke.blog.domain.entity.Category;
import com.luke.blog.repositories.CategoryRespository;
import com.luke.blog.services.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @Override
    public void deleteCategory(UUID id) {
        Optional<Category> category = categoryRespository.findById(id);
        if(category.isPresent()){
            if(!category.get().getPosts().isEmpty()){
                throw new IllegalArgumentException("Category has posts associated with it");
                }
            categoryRespository.deleteById(id);
        }
    }

    @Override
    public Category getCategoryById(UUID id){
        return categoryRespository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Category with id " + id + " not found")
        );
    }
}
