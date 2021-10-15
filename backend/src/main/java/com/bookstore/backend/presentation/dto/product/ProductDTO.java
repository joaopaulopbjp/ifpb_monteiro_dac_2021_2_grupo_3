package com.bookstore.backend.presentation.dto.product;

import java.math.BigDecimal;
import java.util.List;

import com.bookstore.backend.domain.model.author.AuthorModel;
import com.bookstore.backend.domain.model.category.CategoryModel;
import com.bookstore.backend.domain.model.company.PublishingCompanyModel;
import com.bookstore.backend.domain.model.inventory.InventoryModel;
import com.bookstore.backend.domain.model.user.PersonModel;
import com.bookstore.backend.presentation.dto.image.ImageDTO;
import com.bookstore.backend.presentation.dto.sale.SaleDTO;

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

    private List<ImageDTO> imageList;

    private SaleDTO sale;

    private InventoryModel inventory;
    
    private List<CategoryModel> categoryList;

    private List<Long> categoryListId;

    private PersonModel saller;

    private Long sallerId;

    private PublishingCompanyModel company;

    private Long companyId;

    private List<AuthorModel> authorList;

    private List<Long> authorListId;

    // private List<EvaluateDTO> evaluateList;
}
