package com.bookstore.backend.entitys;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class PublishingCompany {
    
	private Long id;
	private String name;
	private List<Book> books = new ArrayList<Book>();
}
