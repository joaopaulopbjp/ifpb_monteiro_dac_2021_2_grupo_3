package com.bookstore.backend.application.service.category;

import java.util.List;
import java.util.Optional;

import com.bookstore.backend.domain.model.category.CategoryModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.service.category.CategoryRepositoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepositoryService categoryRepositoryService;

    public CategoryModel save(CategoryModel categoryModel){
        return categoryRepositoryService.getInstance().save(categoryModel);
    }

    public void delete(CategoryModel categoryModel){
        categoryRepositoryService.getInstance().delete(categoryModel);
    }

    public CategoryModel update(CategoryModel categoryModel) throws NotFoundException{
        return categoryRepositoryService.update(categoryModel);
    }

    public CategoryModel findById(Long id) throws NotFoundException{
        Optional<CategoryModel> category = categoryRepositoryService.getInstance().findById(id);
        if(category == null)
            throw new NotFoundException();
        return category.get();
    }

    public List<CategoryModel> findByName(String name) throws NotFoundException{
        List<CategoryModel> categories = categoryRepositoryService.getInstance().findByName(name);
        if(categories == null){
            throw new NotFoundException();
        }
        return categories;
    }

    public List<CategoryModel> findAll(){
        return categoryRepositoryService.getInstance().findAll();
    }
}
