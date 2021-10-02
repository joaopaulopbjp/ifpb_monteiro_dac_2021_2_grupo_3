package com.bookstore.backend.domain.model.product;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.bookstore.backend.domain.model.author.AuthorModel;
import com.bookstore.backend.domain.model.category.CategoryModel;
import com.bookstore.backend.domain.model.company.PublishingCompanyModel;
import com.bookstore.backend.domain.model.inventory.InventoryModel;
import com.bookstore.backend.domain.model.sale.SaleModel;
import com.bookstore.backend.domain.model.user.PersonModel;

@Entity
@Table(name = "T_MAGAZINE")
public class MagazineModel extends ProductModel{

    public MagazineModel(Long id, String title, String description, int yearLaunch, int pages, BigDecimal price,
            SaleModel sale, InventoryModel inventory, List<CategoryModel> categoryList, PersonModel salesman,
            PublishingCompanyModel company, List<AuthorModel> authorList) {
        super(id, title, description, yearLaunch, pages, price, sale, inventory, categoryList, salesman, company,
                authorList);
    }

    public MagazineModel() {
    }
    
}
