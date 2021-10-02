package com.bookstore.backend.domain.model.product;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.bookstore.backend.domain.model.AuthorModel;
import com.bookstore.backend.domain.model.CategoryModel;
import com.bookstore.backend.domain.model.InventoryModel;
import com.bookstore.backend.domain.model.PublishingCompanyModel;
import com.bookstore.backend.domain.model.sale.SaleModel;
import com.bookstore.backend.domain.model.user.PersonModel;

@Entity
@Table(name = "T_BOOK")
public class BookModel extends ProductModel{ 

    public BookModel(Long id, String title, String description, int yearLaunch, int pages, BigDecimal price,
            SaleModel sale, InventoryModel inventory, List<CategoryModel> categoryList, PersonModel salesman,
            PublishingCompanyModel company, List<AuthorModel> authorList) {
        super(id, title, description, yearLaunch, pages, price, sale, inventory, categoryList, salesman, company,
                authorList);
    }

    public BookModel() {
    }
    
    @Override
    public String toString() {
        return String.format("BOOK [ID: %s - TITLE: %s - DESCRIPTION: %s - YEAR LAUNCH: %s - PAGES: %s - PRICE: %s - COMPANY: %s]", getId(), getTitle(), getDescription(), getYearLaunch(), getPages(), getPrice().toString(), getCompany().getName()); 
    }
}
