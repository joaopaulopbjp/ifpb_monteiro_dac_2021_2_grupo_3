package com.bookstore.backend.presentation.dto.product;

import java.math.BigDecimal;
import java.util.List;

import com.bookstore.backend.domain.model.author.AuthorModel;
import com.bookstore.backend.domain.model.category.CategoryModel;
import com.bookstore.backend.domain.model.company.PublishingCompanyModel;
import com.bookstore.backend.domain.model.evaluation.EvaluateModel;
import com.bookstore.backend.domain.model.inventory.InventoryModel;
import com.bookstore.backend.domain.model.sale.SaleModel;
import com.bookstore.backend.domain.model.user.PersonModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDTO {

    private Long id;

    private String title;

    private String description;

    private Integer yearLaunch;

    private Integer pages;

    private BigDecimal price;

    private List<String> imageList;

    private SaleModel sale;

    private InventoryModel inventory;
    
    private List<CategoryModel> categoryList;

    private PersonModel saller;

    private Long sallerId;

    private PublishingCompanyModel company;

    private List<AuthorModel> authorList;

    private List<EvaluateModel> evaluateList; 
}
