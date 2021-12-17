package com.bookstore.backend.domain.model.product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.bookstore.backend.domain.model.author.AuthorModel;
import com.bookstore.backend.domain.model.category.CategoryModel;
import com.bookstore.backend.domain.model.company.PublishingCompanyModel;
import com.bookstore.backend.domain.model.evaluation.EvaluateModel;
import com.bookstore.backend.domain.model.image.ImageModel;
import com.bookstore.backend.domain.model.inventory.InventoryModel;
import com.bookstore.backend.infrastructure.enumerator.status.Status;
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

    @Column(name = "DESCRIPTION", columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(name = "YEAR_LAUNCH", nullable = false)
    private Integer yearLaunch;

    @Column(name = "PAGES", nullable = false)
    private Integer pages;

    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;

    @Column(name = "STATUS", nullable = false)
    private Status status;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "PRODUCT_FK", nullable = false)
    private List<ImageModel> imageList;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "INVENTORY_FK", nullable = false)
    private InventoryModel inventory;
    
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(
        name = "T_PRODUCT_CATEGORY_JOIN", 
        joinColumns = @JoinColumn(name = "PRODUCT_ID", nullable = false), 
        inverseJoinColumns = @JoinColumn(name = "CATEGORY_ID", nullable = false))
    @Fetch(FetchMode.SUBSELECT)
    private List<CategoryModel> categoryList;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "COMPANY_FK", nullable = false)
    private PublishingCompanyModel company;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
        name = "T_PRODUCT_AUTHOR_JOIN", 
        joinColumns = @JoinColumn(name = "PRODUCT_ID", nullable = false), 
        inverseJoinColumns = @JoinColumn(name = "AUTHOR_ID", nullable = false))
    private List<AuthorModel> authorList;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name = "T_PRODUCT_EVALUATE_JOIN", 
        joinColumns = @JoinColumn(name = "PRODUCT_ID", nullable = false), 
        inverseJoinColumns = @JoinColumn(name = "EVALUATE_ID", nullable = false))
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

    public boolean addImageToImageList(ImageModel image) throws FullListException {
        if(imageList == null) {
            imageList = new ArrayList<>();
            addImageToImageList(image);
        }

        if(imageList.size() < 2) {
            imageList.add(image);
            return true;
        } else {
            throw new FullListException("The list have 2 items");
        }
    }

    public boolean removeImageFromImageList(Integer index) throws NotFoundException {
        if(imageList == null || index > (imageList.size() - 1)) {
            throw new NotFoundException();
        }

        ImageModel removed = imageList.remove(index.intValue());
        if(removed != null) {
            return true;
        }
        return false;
    }

    public boolean addEvaluateToEvaluateList(EvaluateModel evaluateModel) {
        if(evaluateModel != null) {
            evaluateList.add(evaluateModel);
        } else {
            evaluateList = new ArrayList<>();
            addEvaluateToEvaluateList(evaluateModel);
        }
        return true;
    }

    public boolean removeEvaluateFromEvaluateList(EvaluateModel evaluateModel) {
        if(evaluateModel != null) {
            return evaluateList.remove(evaluateModel);
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

    public ImageModel findImageByContent(String contente){
        for(ImageModel image: imageList){
            if(image.getBase64().equals(contente)){
                return image;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return String.format("PRODUCT [ID: %s - TITLE: %s - DESCRIPTION: %s - YEAR LAUNCH: %s - PAGES: %s - PRICE: %s - COMPANY: %s - EVALUATE: [AVERAGE: %s - AVALUATE NUMBER: %s]]", getId(), getTitle(), getDescription(), getYearLaunch(), getPages(), getPrice().toString(), getCompany().getName(), String.valueOf(calculateStarAverage()), String.valueOf(getEvaluateList().size())); 
    }
}
