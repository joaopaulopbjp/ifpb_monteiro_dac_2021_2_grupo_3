package com.bookstore.backend.domain.model;

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
public class PublishingCompanyModel {
    
	private Long id;
	private String name;
	private List<BookModel> books = new ArrayList<BookModel>();
}
