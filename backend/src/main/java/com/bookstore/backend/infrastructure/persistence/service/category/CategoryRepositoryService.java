package com.bookstore.backend.infrastructure.persistence.service.category;

import java.util.NoSuchElementException;

import com.bookstore.backend.domain.model.category.CategoryModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.repository.category.CategoryRepository;
import com.bookstore.backend.infrastructure.utils.Utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class CategoryRepositoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryRepository getInstace() {
        return categoryRepository;
    }

    public CategoryModel update(CategoryModel categoryModel) throws NotFoundException {
        CategoryModel categoryDB = null;
        try {
            categoryDB = categoryRepository.findById(categoryModel.getId()).get();

        } catch (NoSuchElementException e) {
            throw new NotFoundException();
        }

        BeanUtils.copyProperties(categoryModel, categoryDB, Utils.getNullPropertyNames(categoryModel));

        return categoryRepository.save(categoryDB);
    }
}
