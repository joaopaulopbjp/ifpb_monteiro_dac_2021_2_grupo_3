package com.bookstore.backend.presentation.dto.product;

import java.math.BigDecimal;
import java.util.List;

import com.bookstore.backend.infrastructure.enumerator.status.Status;
import com.bookstore.backend.presentation.dto.author.AuthorDTO;
import com.bookstore.backend.presentation.dto.category.CategoryDTO;
import com.bookstore.backend.presentation.dto.company.PublishingCompanyDTO;
import com.bookstore.backend.presentation.dto.evaluate.EvaluateDTO;
import com.bookstore.backend.presentation.dto.image.ImageDTO;
import com.bookstore.backend.presentation.dto.inventory.InventoryDTO;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {

    private Long id;

    private String title;

    private String description;

    private Integer yearLaunch;

    private Integer pages;

    private BigDecimal price;

    private Status status;

    private List<ImageDTO> imageList;

    private InventoryDTO inventory;
    
    private List<CategoryDTO> categoryList;

    private List<Long> idCategoryList;

    private Long idSaller;

    private PublishingCompanyDTO company;

    private Long idCompany;

    private List<AuthorDTO> authorList;

    private List<Long> idAuthorList;

    private List<EvaluateDTO> evaluateList;
}
