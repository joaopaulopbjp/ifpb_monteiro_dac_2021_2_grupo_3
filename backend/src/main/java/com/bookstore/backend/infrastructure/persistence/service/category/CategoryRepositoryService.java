package com.bookstore.backend.infrastructure.persistence.service.category;

import java.util.List;
import java.util.NoSuchElementException;

import com.bookstore.backend.domain.model.category.CategoryModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.repository.category.CategoryRepository;
import com.bookstore.backend.infrastructure.utils.Utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CategoryRepositoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Value("${numberOfItemsPerPage}")
    private String numberOfItemsPerPage;

    public CategoryRepository getInstance() {
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

    public List<CategoryModel> findAll(int pageNumber) throws NotFoundException{
        Pageable pageable = PageRequest.of(pageNumber, Integer.parseInt(numberOfItemsPerPage), Sort.by("name").ascending());
        Page<CategoryModel> pages = categoryRepository.findAll(pageable);

        if(pages.isEmpty()) throw new NotFoundException();
        
        return pages.getContent();
    }
}
