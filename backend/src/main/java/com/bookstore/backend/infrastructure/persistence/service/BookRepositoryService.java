package com.bookstore.backend.infrastructure.persistence.service;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;

import com.bookstore.backend.domain.model.product.BookModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.repository.product.BookRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
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

    public BookModel update(BookModel book) throws NotFoundException {
        BookModel bookDataBase = null;
        try {
            bookDataBase = bookRepository.findById(book.getId()).get();

        } catch (NoSuchElementException e) {
            throw new NotFoundException();
        }

        BeanUtils.copyProperties(book, bookDataBase, getNullPropertyNames(book));

        return bookRepository.save(bookDataBase);
    }

    public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
    
        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
    
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
