package com.luke.blog.domain.services;

import com.luke.blog.domain.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    List<Category> listCategories();
}
