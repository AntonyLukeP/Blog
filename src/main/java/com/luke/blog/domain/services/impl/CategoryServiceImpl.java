package com.luke.blog.domain.services.impl;

import com.luke.blog.domain.entity.Category;
import com.luke.blog.domain.repositories.CategoryRespository;
import com.luke.blog.domain.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRespository categoryRespository;

    @Override
    public List<Category>  listCategories() {
        return categoryRespository.findAllWithPostCount();
    }
}
