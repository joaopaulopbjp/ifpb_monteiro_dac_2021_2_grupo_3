package com.bookstore.backend.infrastructure.persistence.service.author;

import java.util.NoSuchElementException;

import com.bookstore.backend.domain.model.author.AuthorModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.repository.author.AuthorRepository;
import com.bookstore.backend.infrastructure.utils.Utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthorRepositoryService {
    @Autowired
    private AuthorRepository authorRepository;

    public AuthorRepository getInstace() {
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
}
