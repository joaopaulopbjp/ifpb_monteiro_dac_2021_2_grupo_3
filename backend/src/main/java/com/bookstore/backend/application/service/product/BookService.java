package com.bookstore.backend.application.service.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bookstore.backend.domain.model.author.AuthorModel;
import com.bookstore.backend.domain.model.category.CategoryModel;
import com.bookstore.backend.domain.model.company.PublishingCompanyModel;
import com.bookstore.backend.domain.model.product.BookModel;
import com.bookstore.backend.domain.model.user.UserModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.service.author.AuthorRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.category.CategoryRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.company.PublishingCompanyRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.person.UserRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.product.BookRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.sale.RevenuesRepositoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    
    @Autowired
    private BookRepositoryService bookRepositoryService;

    @Autowired
    private CategoryRepositoryService categoryRepositoryService;

    @Autowired
    private AuthorRepositoryService authorRepositoryService;

    @Autowired
    private UserRepositoryService userRepositoryService;

    @Autowired
    private RevenuesRepositoryServices revenuesRepositoryServices;

    @Autowired
    private PublishingCompanyRepositoryService companyRepositoryService;

    public BookModel save(BookModel book, List<Long> categoryListId, Long sallerId, Long companyId, List<Long> authorListId) throws NotFoundException {
        Optional<UserModel> personModelOp = userRepositoryService.getInstance().findById(sallerId);
        Optional<PublishingCompanyModel> companyOp = companyRepositoryService.getInstance().findById(companyId);
        List<CategoryModel> categoryRecoveredList = new ArrayList<>();
        List<AuthorModel> authorRecoveredList = new ArrayList<>();

        for(Long id : categoryListId) {
            CategoryModel category = categoryRepositoryService.getInstance().findById(id).get();
            categoryRecoveredList.add(category);
        }

        for(Long id : authorListId) {
            AuthorModel author = authorRepositoryService.getInstance().findById(id).get();
            authorRecoveredList.add(author);
        }

        if(personModelOp.get() == null) {
            throw new NotFoundException("Not found the id " + sallerId + " for user.");
        }
        book.setCategoryList(categoryRecoveredList);
        book.setAuthorList(authorRecoveredList);
        book.setSaller(personModelOp.get());
        book.setCompany(companyOp.get());

        revenuesRepositoryServices.getInstance().save(book.getSale().getRevenues());
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

    public List<BookModel> findByCategoryIdList(List<Long> idList) throws NotFoundException {
        List<BookModel> bookRecoveredList = bookRepositoryService.findByCategoryIdList(0, idList);
        if(bookRecoveredList.isEmpty()) 
            throw new NotFoundException();
            
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
