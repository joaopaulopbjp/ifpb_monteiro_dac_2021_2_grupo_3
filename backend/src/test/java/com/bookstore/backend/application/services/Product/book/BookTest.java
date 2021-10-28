package com.bookstore.backend.application.services.Product.book;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.backend.application.service.author.AuthorService;
import com.bookstore.backend.application.service.company.PublishingCompanyService;
import com.bookstore.backend.application.service.person.UserService;
import com.bookstore.backend.application.service.product.BookService;
import com.bookstore.backend.domain.model.author.AuthorModel;
import com.bookstore.backend.domain.model.category.CategoryModel;
import com.bookstore.backend.domain.model.company.PublishingCompanyModel;
import com.bookstore.backend.domain.model.image.ImageModel;
import com.bookstore.backend.domain.model.inventory.InventoryModel;
import com.bookstore.backend.domain.model.product.BookModel;
import com.bookstore.backend.domain.model.user.UserModel;
import com.bookstore.backend.infrastructure.enumerator.InventoryStatus;
import com.bookstore.backend.infrastructure.enumerator.status.Status;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.service.category.CategoryRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.product.BookRepositoryService;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class BookTest {
    
    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepositoryService bookRepositoryService;

    @Autowired
    private CategoryRepositoryService categoryRepositoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private PublishingCompanyService publishingCompanyService;

    @Autowired
    private AuthorService authorService;

    @Test
    @Order(1)
    public void saveBookSucess() throws NotFoundException{
        CategoryModel category = new CategoryModel(0l, "adventure");
        category = categoryRepositoryService.getInstance().save(category);
        List<CategoryModel> categoryList = new ArrayList<>();
        categoryList.add(category);
        List<Long> categoryListId = new ArrayList<>();
        categoryListId.add(category.getId());

        ImageModel image = new ImageModel(0l,"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");
        List<ImageModel> imageList = new ArrayList<>();
        imageList.add(image);

        AuthorModel author = new AuthorModel(0l, "pedro");
        author = authorService.save(author);
        List<AuthorModel> authorList = new ArrayList<>();
        authorList.add(author);
        List<Long> authorListId = new ArrayList<>();
        authorListId.add(author.getId());

        UserModel user = new UserModel(0l, "Thauan", "thauan@email.com", "12345", null, null, null, null, null);
        user = userService.save(user);

        PublishingCompanyModel company = new PublishingCompanyModel(0l, "brascabam");
        company = publishingCompanyService.save(company);
        BookModel book = new BookModel(0l, "teste", "livro de fantasia", 2021, 10, new BigDecimal(1.99), Status.ACTIVE, imageList, 
        new InventoryModel(0l, 1000, InventoryStatus.AVAILABLE), categoryList, company, authorList, null);

        List<BookModel> bookList = bookRepositoryService.getInstance().findAll();

        assertEquals(bookList.size()+1, bookService.save(book, categoryListId, user.getId(), company.getId() , authorListId).getId());
    }

    @Test
    @Order(2)
    public void saveBookError() throws NotFoundException{
        CategoryModel category = new CategoryModel(0l, "literatura");
        category = categoryRepositoryService.getInstance().save(category);
        List<CategoryModel> categoryList = new ArrayList<>();
        categoryList.add(category);
        List<Long> categoryListId = new ArrayList<>();
        categoryListId.add(category.getId());

        ImageModel image = new ImageModel(0l,"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");
        List<ImageModel> imageList = new ArrayList<>();
        imageList.add(image);

        AuthorModel author = new AuthorModel(0l, "armando");
        author = authorService.save(author);
        List<AuthorModel> authorList = new ArrayList<>();
        authorList.add(author);
        List<Long> authorListId = new ArrayList<>();
        authorListId.add(author.getId());

        UserModel user = new UserModel(0l, "Arnaldo", "arnaldo@email.com", "12345", null, null, null, null, null);
        user = userService.save(user);

        PublishingCompanyModel company = new PublishingCompanyModel(0l, "literatura de cordel");
        company = publishingCompanyService.save(company);
        BookModel book = new BookModel(0l, "", "livro de fantasia", 2000, 10, new BigDecimal(1.99), Status.ACTIVE, 
        imageList, new InventoryModel(0l, 1000, InventoryStatus.AVAILABLE), categoryList, company, authorList, null);

        Long userId = user.getId();
        Long companyId = company.getId();
        // Test title empty
        assertThrows(IllegalArgumentException.class, () -> {bookService.save(book, categoryListId, userId, companyId, authorListId);});
        
        BookModel book2 = new BookModel(0l, "The adventure in Hell", "livro de fantasia", 2022, 10, new BigDecimal(1.99), Status.ACTIVE, 
        imageList, new InventoryModel(0l, 1000, InventoryStatus.AVAILABLE), categoryList, company, authorList, null);
        // Test yearlaunch 2022
        assertThrows(IllegalArgumentException.class, () -> {bookService.save(book2, categoryListId, userId, companyId, authorListId);});

        BookModel book3 = new BookModel(0l, "The adventure in Hell", "livro de fantasia", 0, 10, new BigDecimal(1.99), Status.ACTIVE, 
        imageList, new InventoryModel(0l, 1000, InventoryStatus.AVAILABLE), categoryList, company, authorList, null);
        // Test yearLaunch 0
        assertThrows(IllegalArgumentException.class, () -> {bookService.save(book3, categoryListId, userId, companyId, authorListId);});

        ImageModel image1 = new ImageModel(0l,"");
        List<ImageModel> imageList1 = new ArrayList<>();
        imageList.add(image1);
        BookModel book4 = new BookModel(0l, "The adventure in Hell", "livro de fantasia", 2021, 10, new BigDecimal(1.99), Status.ACTIVE, 
        imageList1, new InventoryModel(0l, 1000, InventoryStatus.AVAILABLE), categoryList, company, authorList, null);
        // Test imageList empty
        assertThrows(IllegalArgumentException.class, () -> {bookService.save(book4, categoryListId, userId, companyId, authorListId);});

        BookModel book5 = new BookModel(0l, "The adventure in Hell", "livro de fantasia", 2021, -200, new BigDecimal(1.99), Status.ACTIVE, 
        imageList, new InventoryModel(0l, 1000, InventoryStatus.AVAILABLE), categoryList, company, authorList, null);
        // Test pages minor than 0 
        assertThrows(IllegalArgumentException.class, () -> {bookService.save(book5, categoryListId, userId, companyId, authorListId);});

        BookModel book6 = new BookModel(0l, "The adventure in Hell", "livro de fantasia", 2021, 200, new BigDecimal(-100), Status.ACTIVE, 
        imageList, new InventoryModel(0l, 1000, InventoryStatus.AVAILABLE), categoryList, company, authorList, null);
        // Test price minor than 0 
        assertThrows(IllegalArgumentException.class, () -> {bookService.save(book6, categoryListId, userId, companyId, authorListId);});
    }

    @Test
    @Order(3)
    public void deleteSucess() throws NotFoundException{
        List<BookModel> bookList = bookRepositoryService.getInstance().findAll();

        bookService.delete(bookList.get(0).getId());

        assertEquals(bookList.size()-1, bookRepositoryService.getInstance().findAll().size()-1);
    }

    @Test
    @Order(4)
    public void invalidIdsForDelete() throws NotFoundException{
        assertThrows(NotFoundException.class, () -> {bookService.delete(-100000l);});
        assertThrows(NotFoundException.class, () -> {bookService.delete(100000l);});
    }

    @Test
    @Order(5)
    public void findBytitleSucess() throws NotFoundException{
        assertFalse(bookService.findByTitle("teste").isEmpty());
    }

    @Test
    @Order(6)
    public void findByTitleErrorPassing() throws NotFoundException{
        assertThrows(NotFoundException.class, () -> {bookService.findByTitle("");});
    }

    @Test
    @Order(7)
    public void findByTitleErrorNull() throws NotFoundException{
        assertThrows(NotFoundException.class, () -> {bookService.findByTitle(null);});
    }
}
