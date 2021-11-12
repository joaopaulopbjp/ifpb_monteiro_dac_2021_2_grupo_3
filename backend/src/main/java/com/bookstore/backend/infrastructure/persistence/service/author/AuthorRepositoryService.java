package com.bookstore.backend.infrastructure.persistence.service.author;

import java.util.List;
import java.util.NoSuchElementException;

import com.bookstore.backend.domain.model.author.AuthorModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.repository.author.AuthorRepository;
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
public class AuthorRepositoryService {

    @Autowired
    private AuthorRepository authorRepository;

    @Value("${numberOfItemsPerPage}")
    private String numberOfItemsPerPage;

    public AuthorRepository getInstance() {
        return authorRepository;
    }

    public AuthorModel update(AuthorModel authorModel) throws NotFoundException {
        AuthorModel authorDB = null;
        try {
            authorDB = authorRepository.findById(authorModel.getId()).get();

        } catch (NoSuchElementException e) {
            throw new NotFoundException();
        }

        BeanUtils.copyProperties(authorModel, authorDB, Utils.getNullPropertyNames(authorModel));

        return authorRepository.save(authorDB);
    }

    public List<AuthorModel> findAll(int pageNumber) throws NotFoundException{
        Pageable pageable = PageRequest.of(pageNumber, Integer.parseInt(numberOfItemsPerPage), Sort.by("name").ascending());
        Page<AuthorModel> pages = authorRepository.findAll(pageable);

        if(pages.isEmpty()) throw new NotFoundException();
        
        return pages.getContent();
    }
}
