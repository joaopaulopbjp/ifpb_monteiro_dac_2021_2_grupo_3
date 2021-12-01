package com.bookstore.backend.infrastructure.persistence.service.product;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.bookstore.backend.domain.model.category.CategoryModel;
import com.bookstore.backend.domain.model.product.BookModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.repository.category.CategoryRepository;
import com.bookstore.backend.infrastructure.persistence.repository.product.BookRepository;
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
public class BookRepositoryService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Value("${numberOfItemsPerPage}")
    private String numberOfItemsPerPage;

    public BookRepository getInstance() {
        return bookRepository;
    }

    public Integer getTotalPages() {
        return (int) Math.floor(bookRepository.findAll().size() / Integer.parseInt(numberOfItemsPerPage));
    }

    public List<BookModel> findCheapests(int quantity) throws NotFoundException {
        Pageable pageable = PageRequest.of(0, quantity, Sort.by("price").ascending());

        Page<BookModel> pages = bookRepository.findAllInventoryAvailable(pageable);
        
        if(pages.isEmpty()) throw new NotFoundException();
        
        return pages.getContent();
    }

    public List<BookModel> findAll(int pageNumber) throws NotFoundException {
        Pageable pageable = PageRequest.of(pageNumber, Integer.parseInt(numberOfItemsPerPage), Sort.by("title").ascending());
        Page<BookModel> pages = bookRepository.findAll(pageable);

        if(pages.isEmpty()) throw new NotFoundException();
        
        return pages.getContent();
    }

    public List<BookModel> findByCategoryId(Long categoryId) throws NotFoundException {

        Optional<CategoryModel> categoryOp = categoryRepository.findById(categoryId);
        List<CategoryModel> categoryList = new ArrayList<>();
        categoryList.add(categoryOp.get());

        List<BookModel> bookRecoveredList = bookRepository.findByCategoryId(categoryOp.get());

        if(bookRecoveredList.isEmpty()) throw new NotFoundException();

        return bookRecoveredList;
    }

    public BookModel update(BookModel book) throws NotFoundException {
        BookModel bookDataBase = null;
        try {
            bookDataBase = bookRepository.findById(book.getId()).get();

        } catch (NoSuchElementException e) {
            throw new NotFoundException();
        }

        BeanUtils.copyProperties(book, bookDataBase, Utils.getNullPropertyNames(book));

        return bookRepository.save(bookDataBase);
    }

    public List<BookModel> findBooksAvailable(int pageNumber) throws NotFoundException{
        Pageable pageable = PageRequest.of(pageNumber, Integer.parseInt(numberOfItemsPerPage), Sort.by("title").ascending());
        Page<BookModel> pages = bookRepository.findAllInventoryAvailable(pageable);

        if(pages.isEmpty()) throw new NotFoundException();
        
        return pages.getContent();
    }

    public List<BookModel> findBooksUnavailable(int pageNumber) throws NotFoundException{
        Pageable pageable = PageRequest.of(pageNumber, Integer.parseInt(numberOfItemsPerPage), Sort.by("title").ascending());
        Page<BookModel> pages = bookRepository.findAllInventoryUnavailable(pageable);

        if(pages.isEmpty()) throw new NotFoundException();
        
        return pages.getContent();
    }  
}
