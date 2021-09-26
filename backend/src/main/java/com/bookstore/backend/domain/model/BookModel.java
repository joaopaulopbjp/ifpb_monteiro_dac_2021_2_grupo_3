package com.bookstore.backend.domain.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
public class BookModel {
    
    private Long id;
    private String title;
    private String description;
    private int yearLaunch;
    private int pages;
    private BigDecimal price;
    private Sale sale;
    private InventoryModel inventory;
    private List<CategoryModel> categoryList;
    private PublishingCompanyModel company;
    private List<AuthorModel> authorList;
}
