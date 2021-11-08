package com.bookstore.backend.application.service.product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bookstore.backend.domain.model.author.AuthorModel;
import com.bookstore.backend.domain.model.category.CategoryModel;
import com.bookstore.backend.domain.model.company.PublishingCompanyModel;
import com.bookstore.backend.domain.model.image.ImageModel;
import com.bookstore.backend.domain.model.product.BookModel;
import com.bookstore.backend.domain.model.sale.SaleModel;
import com.bookstore.backend.domain.model.user.AdminModel;
import com.bookstore.backend.domain.model.user.UserModel;
import com.bookstore.backend.infrastructure.enumerator.status.Status;
import com.bookstore.backend.infrastructure.exception.InvalidValueException;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.service.author.AuthorRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.category.CategoryRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.company.PublishingCompanyRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.person.AdminRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.person.UserRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.product.BookRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.sale.SaleRepositoryService;
import com.bookstore.backend.infrastructure.utils.AdminVerify;

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
    private AdminRepositoryService adminRepositoryService;

    @Autowired
    private SaleRepositoryService saleRepositoryService;

    @Autowired
    private PublishingCompanyRepositoryService companyRepositoryService;

    @Autowired
    private AdminVerify adminVerify;

    public BookModel save(BookModel book, List<Long> categoryListId, Long companyId, List<Long> authorListId, String username) throws NotFoundException, Exception {
        Optional<AdminModel> adminOp = adminRepositoryService.getInstance().findByUsername(username);
        Optional<PublishingCompanyModel> companyOp = companyRepositoryService.getInstance().findById(companyId);
        List<CategoryModel> categoryRecoveredList = new ArrayList<>();
        List<AuthorModel> authorRecoveredList = new ArrayList<>();

        book.setStatus(Status.ACTIVE);

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

        if(book.getTitle() == null){
            throw new IllegalArgumentException("Title is null");
        }

        if(book.getImageList().isEmpty()){
            throw new IllegalArgumentException("The ImageList is empty");
        }

        for(ImageModel image: book.getImageList()){
            if(image.getBase64() == null || image.getBase64().equals(""))
                throw new IllegalArgumentException("Image is empty");
        }

        if(!companyOp.isPresent()) {
            throw new NotFoundException("Not found the id " + companyId + " for PublishingCompany.");
        }

        validate(book);

        book.setCategoryList(categoryRecoveredList);
        book.setAuthorList(authorRecoveredList);
        book.setCompany(companyOp.get());
        BookModel bookSaved = bookRepositoryService.getInstance().save(book);
        adminOp.get().addProductToProductList(bookSaved);
        adminRepositoryService.getInstance().save(adminOp.get());

        SaleModel sale = new SaleModel(0l, bookSaved, 0);
        saleRepositoryService.getInstance().save(sale);
        return bookSaved;
    }

    public BookModel update(BookModel book, String username) throws NotFoundException {
        Optional<AdminModel> adminOp = adminRepositoryService.getInstance().findByUsername(username);
        boolean flag = adminOp.get().getProductForSaleList().stream().filter(personBook -> personBook.getId()==book.getId()).findFirst().isPresent();
        if(!flag){
            throw new NotFoundException("You can't update this book because it belongs to another admin");
        }

        validate(book);

        BookModel bookUpdated = bookRepositoryService.update(book);
        
        return bookUpdated;
    }

    public void delete(Long id, String username) throws Exception {
        boolean flag = bookRepositoryService.getInstance().existsById(id);
        if(!flag)
            throw new NotFoundException("Not found book with id " + id);
        
        BookModel book = bookRepositoryService.getInstance().findById(id).get();

        Optional<AdminModel> adminOp = adminRepositoryService.getInstance().findByUsername(username);
        flag = adminOp.get().getProductForSaleList().stream().filter(personBook -> personBook.getId()==book.getId()).findFirst().isPresent();
        if(!flag){
            throw new NotFoundException("You can't delete this book because it belongs to another admin");
        }

        if(book.getStatus()==Status.INACTIVE) {
            throw new Exception("You can't delete this Book with id " + id + " because it is inactive.");
        }
        book.setStatus(Status.INACTIVE);
        adminOp.get().removeProductFromProductList(book);
        adminRepositoryService.getInstance().save(adminOp.get());
        bookRepositoryService.getInstance().save(book);
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

    public List<BookModel> findByCategoryId(Long idCategory) throws NotFoundException {
        if(!categoryRepositoryService.getInstance().existsById(idCategory))
            throw new NotFoundException("Category with id " + idCategory + " not found");

        List<BookModel> bookRecoveredList = bookRepositoryService.findByCategoryId(idCategory);
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

    public List<BookModel> findBooksAvailable(int pageNumber) throws NotFoundException{
        return bookRepositoryService.findBooksAvailable(pageNumber);
    }

    public List<BookModel> findBooksUnavailable(int pageNumber) throws NotFoundException{
        return bookRepositoryService.findBooksUnavailable(pageNumber);
    }

    private void validate(BookModel book) throws IllegalArgumentException{
        
        if(book.getPrice() != null && book.getPrice().doubleValue() < 0){
            throw new IllegalArgumentException("price can't be minor than 0");
        }

        if(book.getPages() != null && book.getPages() <= 0){
            throw new IllegalArgumentException("pages can't be minor or equal than 0");
        }

        if(book.getPages() != null && book.getYearLaunch() <= 0){
            throw new IllegalArgumentException("yearLaunch can't be minor than 0");
        }

        if(book.getPages() != null && book.getYearLaunch() > LocalDate.now().getYear()){
            throw new IllegalArgumentException("yearLaunch can't be greater than " + LocalDate.now().getYear());
        }

        if(book.getTitle() != null && book.getTitle().length() < 5){
            throw new IllegalArgumentException("The title size must be greater than five.");
        }

    }
}
