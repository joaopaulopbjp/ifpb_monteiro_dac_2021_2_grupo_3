package com.bookstore.backend.infrastructure.persistence.service;

import java.util.List;

import com.bookstore.backend.domain.model.product.BookModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.repository.product.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BookRepositoryService {
    @Autowired
    private BookRepository bookRepository;

    public List<BookModel> findCheapests(int quantity) throws NotFoundException {
        Pageable pageable = PageRequest.of(0, quantity, Sort.by("price").ascending());

        Page<BookModel> pages = bookRepository.findAll(pageable);
        
        if(pages.isEmpty()) throw new NotFoundException();
        
        return pages.getContent();
    }

    public List<BookModel> findAll(int pageNumber) throws NotFoundException {
        Pageable pageable = PageRequest.of(pageNumber, 2, Sort.by("title").ascending());
        Page<BookModel> pages = bookRepository.findAll(pageable);

        if(pages.isEmpty()) throw new NotFoundException();
        
        return pages.getContent();
    }
}
