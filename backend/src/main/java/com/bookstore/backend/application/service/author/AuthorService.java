package com.bookstore.backend.application.service.author;

import java.util.List;
import java.util.Optional;

import com.bookstore.backend.application.service.product.BookService;
import com.bookstore.backend.domain.model.author.AuthorModel;
import com.bookstore.backend.domain.model.product.BookModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.service.author.AuthorRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.product.BookRepositoryService;
import com.bookstore.backend.infrastructure.utils.AdminVerify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    
    @Autowired
    private AuthorRepositoryService authorRepositoryService;

    @Autowired
    private BookRepositoryService bookRepositoryService;

    @Autowired
    private BookService bookService;

    public AuthorModel save(AuthorModel authorModel, String username){
        return authorRepositoryService.getInstance().save(authorModel);
    }

    public void delete(Long id, String username) throws Exception{
        if(!authorRepositoryService.getInstance().existsById(id)){
            throw new NotFoundException("not Found Author. " + id);
        }
        AuthorModel author = authorRepositoryService.getInstance().findById(id).get();
        List<BookModel> bookList = bookRepositoryService.getInstance().findByAuthorId(id);
        for(BookModel book: bookList){
            book.removeAuthorFromAuthorList(author);
            if(book.getAuthorList().isEmpty()){
                bookService.delete(book.getId(), username);
            }else{
                bookRepositoryService.getInstance().save(book);
            }
        }
        authorRepositoryService.getInstance().deleteById(id);
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
