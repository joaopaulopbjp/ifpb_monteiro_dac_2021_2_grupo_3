package com.bookstore.backend.application.service.category;

import java.util.List;
import java.util.Optional;

import com.bookstore.backend.application.service.product.BookService;
import com.bookstore.backend.domain.model.category.CategoryModel;
import com.bookstore.backend.domain.model.product.BookModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.service.category.CategoryRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.product.BookRepositoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepositoryService categoryRepositoryService;

    @Autowired
    private BookRepositoryService bookRepositoryService;

    @Autowired
    private BookService bookService;

    public CategoryModel save(CategoryModel categoryModel) throws IllegalArgumentException{
        if(categoryModel.getName().isEmpty()){
            throw new IllegalArgumentException("name can't not be Empty");
        }
        return categoryRepositoryService.getInstance().save(categoryModel);
    }

    public void delete(Long id, String username) throws Exception{
        if(!categoryRepositoryService.getInstance().existsById(id)){
            throw new NotFoundException("not Found category. " + id);
        }
        CategoryModel category = categoryRepositoryService.getInstance().findById(id).get();
        List<BookModel> bookList = bookRepositoryService.getInstance().findByCategoryId(id);
        for(BookModel book: bookList){
            book.removeCategoryFromCategoryList(category);
            if(book.getCategoryList().isEmpty()){
                bookService.delete(book.getId(), username);
            }else{
                bookRepositoryService.getInstance().save(book);
            }
        }
        categoryRepositoryService.getInstance().deleteById(id);
    }

    public CategoryModel update(CategoryModel categoryModel) throws NotFoundException{
        if(categoryModel.getName().isEmpty()){
            throw new IllegalArgumentException("name can't not be Empty");
        }
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
