package com.bookstore.backend.application.service.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bookstore.backend.domain.model.author.AuthorModel;
import com.bookstore.backend.domain.model.category.CategoryModel;
import com.bookstore.backend.domain.model.company.PublishingCompanyModel;
import com.bookstore.backend.domain.model.product.BookModel;
import com.bookstore.backend.domain.model.product.ProductModel;
import com.bookstore.backend.domain.model.sale.SaleModel;
import com.bookstore.backend.domain.model.user.UserModel;
import com.bookstore.backend.infrastructure.exception.InvalidValueException;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.service.author.AuthorRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.category.CategoryRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.company.PublishingCompanyRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.person.UserRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.product.BookRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.sale.SaleRepositoryService;

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
    private SaleRepositoryService saleRepositoryService;

    @Autowired
    private PublishingCompanyRepositoryService companyRepositoryService;

    public BookModel save(BookModel book, List<Long> categoryListId, Long sallerId, Long companyId, List<Long> authorListId) throws NotFoundException {
        Optional<UserModel> personModelOp = userRepositoryService.getInstance().findById(sallerId);
        Optional<PublishingCompanyModel> companyOp = companyRepositoryService.getInstance().findById(companyId);
        List<CategoryModel> categoryRecoveredList = new ArrayList<>();
        List<AuthorModel> authorRecoveredList = new ArrayList<>();

        for(Long id : categoryListId) {
            Optional<CategoryModel> category = categoryRepositoryService.getInstance().findById(id);
            if(!category.isPresent())
                throw new NotFoundException("Not found the id " + id + " for Category.");
            categoryRecoveredList.add(category.get());
        }

        for(Long id : authorListId) {
            Optional<AuthorModel> author = authorRepositoryService.getInstance().findById(id);
            if(!author.isPresent())
                throw new NotFoundException("Not found the id " + id + " for Author.");
            authorRecoveredList.add(author.get());
        }

        if(!personModelOp.isPresent()) {
            throw new NotFoundException("Not found the id " + sallerId + " for user.");
        }
        if(!companyOp.isPresent()) {
            throw new NotFoundException("Not found the id " + companyId + " for PublishingCompany.");

        }
        book.setCategoryList(categoryRecoveredList);
        book.setAuthorList(authorRecoveredList);
        book.setCompany(companyOp.get());

        BookModel bookSaved = bookRepositoryService.getInstance().save(book);
        personModelOp.get().addProductToProductList(bookSaved);
        userRepositoryService.getInstance().save(personModelOp.get());

        SaleModel sale = new SaleModel(0l, bookSaved, 0);
        saleRepositoryService.getInstance().save(sale);
        return bookSaved;
    }

    public BookModel update(BookModel book) throws NotFoundException {
        BookModel bookUpdated = bookRepositoryService.update(book);
        return bookUpdated;
    }

    public void delete(Long id) throws NotFoundException {
        boolean flag = bookRepositoryService.getInstance().existsById(id);
        if(!flag)
        throw new NotFoundException();
        
        UserModel user = userRepositoryService.getInstance().findByProductId(id);
        ProductModel product = bookRepositoryService.getInstance().findById(id).get();
        user.removeProductFromProductList(product);

        SaleModel sale = saleRepositoryService.getInstance().findByProductId(id);
        
        userRepositoryService.getInstance().save(user);
        saleRepositoryService.getInstance().deleteById(sale.getId());

    }

    public BookModel findById(Long id) throws NotFoundException {
        Optional<BookModel> bookRecovered = bookRepositoryService.getInstance().findById(id);
        if(!bookRecovered.isPresent()) 
            throw new NotFoundException();
            
        return bookRecovered.get();
    }

    public List<BookModel> findByTitle(String titleToFind) throws NotFoundException {
        List<BookModel> bookRecoveredList = bookRepositoryService.getInstance().findByTitle(titleToFind);
        if(bookRecoveredList.isEmpty()) 
            throw new NotFoundException();
            
        return bookRecoveredList;
    }

    public List<BookModel> findByCategoryIdList(int page, List<Long> idList) throws NotFoundException {
        List<BookModel> bookRecoveredList = bookRepositoryService.findByCategoryIdList(page, idList);
        if(bookRecoveredList.isEmpty()) 
            throw new NotFoundException();
            
        return bookRecoveredList;
    }

    public List<BookModel> findCheapests(int number) throws NotFoundException, InvalidValueException {
        if(number <= 0)
            throw new InvalidValueException(number + " is a invalid number for page");
        List<BookModel> bookRecoveredList = bookRepositoryService.findCheapests(number);
        return bookRecoveredList;
    }

    public List<BookModel> findAll(int pageNumber) throws NotFoundException {
        List<BookModel> bookRecoveredList = bookRepositoryService.findAll(pageNumber);

        if(bookRecoveredList.isEmpty()) 
            throw new NotFoundException();
        return bookRecoveredList;
    }
}
