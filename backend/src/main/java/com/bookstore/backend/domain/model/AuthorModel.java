package com.bookstore.backend.domain.model;

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

    public boolean addBookToBookList(BookModel book) {
        if(bookList != null){
            this.bookList.add(book);
        } else {
            bookList = new ArrayList<>();
            addBookToBookList(book);
        }
        return true;
    }

    public boolean removeBookFromBookList(BookModel book) {
        if(bookList != null) {
            return bookList.remove(book);
        }
        return false;
    }

}
