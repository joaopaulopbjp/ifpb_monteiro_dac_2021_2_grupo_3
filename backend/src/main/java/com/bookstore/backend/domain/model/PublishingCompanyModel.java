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
	private List<BookModel> bookList;

	public boolean addBookToBookList(BookModel bookModel) {
		if(bookList != null) {
			bookList.add(bookModel);
		} else {
			bookList = new ArrayList<>();
			addBookToBookList(bookModel);
		}
		return true;
	}

	public boolean removeBookFromBookList(BookModel bookModel) {
		if(bookList != null) {
			return bookList.remove(bookModel);
		}
		 return false;
	}
}
