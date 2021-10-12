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
import com.bookstore.backend.infrastructure.exception.FullListException;
import com.bookstore.backend.infrastructure.exception.NotFoundException;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "YEAR_LAUNCH", nullable = false)
    private Integer yearLaunch;

    @Column(name = "PAGES", nullable = false)
    private Integer pages;

    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;

    @OneToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "IMAGES", nullable = false)
    private List<String> imageList;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    @JoinColumn(name = "SALE_FK", nullable = false)
    private SaleModel sale;
    
    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    @JoinColumn(name = "INVENTORY_FK", nullable = false)
    private InventoryModel inventory;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "T_PRODUCT_CATEGORY_JOIN", 
        joinColumns = @JoinColumn(name = "PRODUCT_ID", nullable = false), 
        inverseJoinColumns = @JoinColumn(name = "CATEGORY_ID", nullable = false))
    private List<CategoryModel> categoryList;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SALLER_FK", nullable = false)
    private PersonModel saller;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "COMPANY_FK", nullable = false)
    private PublishingCompanyModel company;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "T_PRODUCT_AUTHOR_JOIN", 
        joinColumns = @JoinColumn(name = "PRODUCT_ID", nullable = false), 
        inverseJoinColumns = @JoinColumn(name = "AUTHOR_ID", nullable = false))
    private List<AuthorModel> authorList;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
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

    public boolean addImageToImageList(String imageBase64) throws FullListException {
        if(imageList == null) {
            imageList = new ArrayList<>();
            addImageToImageList(imageBase64);
        }

        if(imageList.size() < 2) {
            imageList.add(imageBase64);
            return true;
        } else {
            throw new FullListException("The list have 2 items");
        }
    }

    public boolean removeImageFromImageList(Integer index) throws NotFoundException {
        if(imageList == null || index > (imageList.size() - 1)) {
            throw new NotFoundException();
        }

        String removed = imageList.remove(index.intValue());
        if(removed != null) {
            return true;
        }
        return false;
    }

    public int calculateStarAverage() {
        if(evaluateList != null && evaluateList.size() > 0) {
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
        return String.format("PRODUCT [ID: %s - TITLE: %s - DESCRIPTION: %s - YEAR LAUNCH: %s - PAGES: %s - PRICE: %s - COMPANY: %s - EVALUATE: [AVERAGE: %s - AVALUATE NUMBER: %s]]", getId(), getTitle(), getDescription(), getYearLaunch(), getPages(), getPrice().toString(), getCompany().getName(), String.valueOf(calculateStarAverage()), String.valueOf(getEvaluateList().size())); 
    }
}
