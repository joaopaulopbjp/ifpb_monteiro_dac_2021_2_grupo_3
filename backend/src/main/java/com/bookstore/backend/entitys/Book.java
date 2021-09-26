package com.bookstore.backend.entitys;

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
public class Book {
    
    private Long id;
    private String title;
    private String description;
    private int yearLaunch;
    private int pages;
    private Double price;
    private Category category;
    private PublishingCompany company;
    private List<Author> authores = new ArrayList<Author>();
}
