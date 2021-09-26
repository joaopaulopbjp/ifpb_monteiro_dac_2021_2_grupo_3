package com.bookstore.backend.domain.model;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

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
public class AuthorModel {
    
    private Long id;
    private String name;
    private List<BookModel> bookList;

}
