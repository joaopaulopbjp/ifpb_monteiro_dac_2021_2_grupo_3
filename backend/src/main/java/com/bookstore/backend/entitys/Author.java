package com.bookstore.backend.entitys;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Lombok
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class Author {
    
    private Long id;
    private String name;
    private Date dateBirth;
    private List<Book> books = new ArrayList<Book>();

}
