package com.bookstore.backend.application.service.author;

import java.util.List;
import java.util.Optional;

import com.bookstore.backend.domain.model.author.AuthorModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.service.author.AuthorRepositoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    
    @Autowired
    private AuthorRepositoryService authorRepositoryService;

    public AuthorModel save(AuthorModel authorModel){
        return authorRepositoryService.getInstance().save(authorModel);
    }

    public void delete(Long id) throws IllegalArgumentException{
        AuthorModel author = authorRepositoryService.getInstance().findById(id).get();
        authorRepositoryService.getInstance().delete(author);
    }

    public AuthorModel update(AuthorModel authorModel) throws NotFoundException{
        return authorRepositoryService.update(authorModel);
    }

    public AuthorModel findById(Long id) throws NotFoundException{
        Optional<AuthorModel> author = authorRepositoryService.getInstance().findById(id);
        if(author == null){
            throw new NotFoundException();
        }
        return author.get();
    }

    public List<AuthorModel> findByName(String name) throws NotFoundException{
        List<AuthorModel> author = authorRepositoryService.getInstance().findByName(name);
        if(author == null){
            throw new NotFoundException();
        }
        return author;
    }

    public List<AuthorModel> findAll(){
        return authorRepositoryService.getInstance().findAll();
    }
}
