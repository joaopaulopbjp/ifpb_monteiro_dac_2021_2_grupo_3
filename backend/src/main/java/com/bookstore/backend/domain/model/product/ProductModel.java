package com.bookstore.backend.domain.model.product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.bookstore.backend.domain.model.author.AuthorModel;
import com.bookstore.backend.domain.model.category.CategoryModel;
import com.bookstore.backend.domain.model.company.PublishingCompanyModel;
import com.bookstore.backend.domain.model.evaluation.EvaluateModel;
import com.bookstore.backend.domain.model.inventory.InventoryModel;
import com.bookstore.backend.domain.model.sale.SaleModel;
import com.bookstore.backend.domain.model.user.PersonModel;

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
public abstract class ProductModel {
    
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

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private SaleModel sale;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private InventoryModel inventory;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "T_PRODUCT_CATEGORY_JOIN", 
        joinColumns = @JoinColumn(name = "PRODUCT_ID"), 
        inverseJoinColumns = @JoinColumn(name = "CATEGORY_ID"))
    private List<CategoryModel> categoryList;

    @ManyToOne(cascade = CascadeType.ALL)
    private PersonModel salesman;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "COMPANY_FK")
    private PublishingCompanyModel company;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "T_PRODUCT_AUTHOR_JOIN", 
        joinColumns = @JoinColumn(name = "PRODUCT_ID"), 
        inverseJoinColumns = @JoinColumn(name = "AUTHOR_ID"))
    private List<AuthorModel> authorList;

    @OneToMany(mappedBy = "product")
    private List<EvaluateModel> evaluateList; 

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

    public int calculateStarAverage() {
        if(evaluateList.size() > 0) {
            int total = 0;
            for(EvaluateModel evaluate : evaluateList) {
                total += evaluate.getStarNumber();
            }
            return (total / evaluateList.size());
        }
        return 0;
    }

    @Override
    public String toString() {
        return String.format("PRODUCT [ID: %s - TITLE: %s - DESCRIPTION: %s - YEAR LAUNCH: %s - PAGES: %s - PRICE: %s - COMPANY: %s - EVALUATE: %s]", getId(), getTitle(), getDescription(), getYearLaunch(), getPages(), getPrice().toString(), getCompany().getName(), String.valueOf(calculateStarAverage())); 
    }
}
