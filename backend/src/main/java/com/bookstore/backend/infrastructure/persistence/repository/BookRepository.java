package com.bookstore.backend.infrastructure.persistence.repository;

import com.bookstore.backend.domain.model.BookModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookModel, Long> {
        
}

