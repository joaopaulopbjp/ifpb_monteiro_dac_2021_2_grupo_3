package com.bookstore.backend.application.service.product;

import java.util.List;
import java.util.Optional;

import com.bookstore.backend.domain.model.category.CategoryModel;
import com.bookstore.backend.domain.model.product.BookModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.service.product.BookRepositoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    
    @Autowired
    private BookRepositoryService bookRepositoryService;

    public BookModel save(BookModel book) {
        BookModel bookSaved = bookRepositoryService.getInstance().save(book);
        return bookSaved;
    }

    public BookModel update(BookModel book) throws NotFoundException {
        BookModel bookUpdated = bookRepositoryService.update(book);
        return bookUpdated;
    }

    public void delete(Long id) throws IllegalArgumentException{
        bookRepositoryService.getInstance().deleteById(id);
    }

    public BookModel findById(Long id) throws NotFoundException {
        Optional<BookModel> bookRecovered = bookRepositoryService.getInstance().findById(id);
        if(bookRecovered.get() == null) 
            throw new NotFoundException();
            
        return bookRecovered.get();
    }

    public List<BookModel> findByTitle(String titleToFind) throws NotFoundException {
        List<BookModel> bookRecoveredList = bookRepositoryService.getInstance().findByTitle(titleToFind);
        if(bookRecoveredList.isEmpty()) 
            throw new NotFoundException();
            
        return bookRecoveredList;
    }

    public List<BookModel> findByCategoryId(Long id) throws NotFoundException {
        List<BookModel> bookRecoveredList = bookRepositoryService.getInstance().findByCategoryId(id);
        if(bookRecoveredList.isEmpty()) 
            throw new NotFoundException();
            
        return bookRecoveredList;
    }

    public List<BookModel> findByCategoryList(List<CategoryModel> categoryToFind) throws NotFoundException {
        List<BookModel> bookRecoveredList = bookRepositoryService.findByCategoryList(categoryToFind);
        
        return bookRecoveredList;
    }

    public List<BookModel> findFiveCheapests() throws NotFoundException {
        List<BookModel> bookRecoveredList = bookRepositoryService.findCheapests(5);
        return bookRecoveredList;
    }

    public List<BookModel> findAll() {
        List<BookModel> bookRecoveredList = bookRepositoryService.getInstance().findAll();
        return bookRecoveredList;
    }
}
