package com.bookstore.backend.domain.model.product;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.bookstore.backend.domain.model.author.AuthorModel;
import com.bookstore.backend.domain.model.category.CategoryModel;
import com.bookstore.backend.domain.model.company.PublishingCompanyModel;
import com.bookstore.backend.domain.model.evaluation.EvaluateModel;
import com.bookstore.backend.domain.model.image.ImageModel;
import com.bookstore.backend.domain.model.inventory.InventoryModel;
import com.bookstore.backend.domain.model.sale.SaleModel;
import com.bookstore.backend.domain.model.user.PersonModel;

@Entity
@Table(name = "T_BOOK")
public class BookModel extends ProductModel{ 

    public BookModel(Long id, String title, String description, Integer yearLaunch, Integer pages, BigDecimal price,
            List<ImageModel> imageList, SaleModel sale, InventoryModel inventory, List<CategoryModel> categoryList,
            PersonModel saller, PublishingCompanyModel company, List<AuthorModel> authorList,
            List<EvaluateModel> evaluateList) {
        super(id, title, description, yearLaunch, pages, price, imageList, sale, inventory, categoryList, saller,
                company, authorList, evaluateList);
    }

    public BookModel() {
    }
    
    @Override
    public String toString() {
        return String.format("BOOK [ID: %s - TITLE: %s - DESCRIPTION: %s - YEAR LAUNCH: %s - PAGES: %s - PRICE: %s - COMPANY: %s - EVALUATE: [AVERAGE: %s - AVALUATE NUMBER: %s]]", getId(), getTitle(), getDescription(), getYearLaunch(), getPages(), getPrice().toString(), getCompany().getName(), String.valueOf(calculateStarAverage()), String.valueOf(getEvaluateList().size())); 
    }
}
