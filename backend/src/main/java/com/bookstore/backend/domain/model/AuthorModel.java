package com.bookstore.backend.domain.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

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
@Entity
@Table(name = "T_AUTHOR")
public class AuthorModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @ManyToMany
    @JoinColumn(name = "AUTHOR_FK")
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
