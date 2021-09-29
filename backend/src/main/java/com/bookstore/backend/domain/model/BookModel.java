package com.bookstore.backend.domain.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
@Table(name = "T_BOOK")
public class BookModel {
    
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
    @OneToOne(mappedBy = "book")
    private SaleModel sale;
    @OneToOne(mappedBy = "book")
    private InventoryModel inventory;
    @ManyToMany(mappedBy = "bookList")
    private List<CategoryModel> categoryList;
    @ManyToOne
    @JoinColumn(name = "COMPANY_FK")
    private PublishingCompanyModel company;
    @ManyToMany(mappedBy = "bookList")
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
