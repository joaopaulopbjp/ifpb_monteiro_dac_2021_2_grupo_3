package com.bookstore.backend.domain.model.product;

import java.math.BigDecimal;
import java.util.List;

import com.bookstore.backend.domain.model.AuthorModel;
import com.bookstore.backend.domain.model.CategoryModel;
import com.bookstore.backend.domain.model.InventoryModel;
import com.bookstore.backend.domain.model.PublishingCompanyModel;
import com.bookstore.backend.domain.model.sale.SaleModel;

public class BookModel extends ProductModel{

    public BookModel(Long id, String title, String description, int yearLaunch, int pages, BigDecimal price,
            SaleModel sale, InventoryModel inventory, List<CategoryModel> categoryList, PublishingCompanyModel company,
            List<AuthorModel> authorList) {
        super(id, title, description, yearLaunch, pages, price, sale, inventory, categoryList, company, authorList);
    }

    public BookModel() {
    }
    
}
