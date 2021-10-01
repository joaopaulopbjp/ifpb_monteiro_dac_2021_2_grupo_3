package com.bookstore.backend.domain.model.product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.bookstore.backend.domain.model.*;
import com.bookstore.backend.domain.model.sale.SaleModel;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "T_PRODUCT")
@Inheritance(strategy = InheritanceType.JOINED)
public class ProductModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "YEAR_LAUNCH")
    private int yearLaunch;
    @Column(name = "PAGES")
    private int pages;
    @Column(name = "PRICE")
    private BigDecimal price;
    @OneToOne(mappedBy = "product")
    private SaleModel sale;
    @OneToOne(mappedBy = "product")
    private InventoryModel inventory;
    @ManyToMany(mappedBy = "productList")
    private List<CategoryModel> categoryList;
    @ManyToOne
    @JoinColumn(name = "COMPANY_FK")
    private PublishingCompanyModel company;
    @ManyToMany(mappedBy = "productList")
    private List<AuthorModel> authorList;

    public boolean addCategoryToCategoryList(CategoryModel categoryModel) {
        if(categoryList != null) {
            categoryList.add(categoryModel);
        } else {
            categoryList = new ArrayList<>();
            addCategoryToCategoryList(categoryModel);
        }
        return true;
    }

    public boolean removeCategoryFromCategoryList(CategoryModel categoryModel) {
        if(categoryList != null) {
            return categoryList.remove(categoryModel);
        }
        return false;
    }

    public boolean addAuthorToAuthorList(AuthorModel authorModel) {
        if(authorList != null) {
            authorList.add(authorModel);
        } else {
            authorList = new ArrayList<>();
            addAuthorToAuthorList(authorModel);
        }
        return true;
    }

    public boolean removeAuthorFromAuthorList(AuthorModel authorModel) {
        if(authorList != null) {
            return authorList.remove(authorModel);
        }
        return false;
    }
}
