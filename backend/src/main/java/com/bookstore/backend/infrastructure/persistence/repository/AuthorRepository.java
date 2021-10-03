package com.bookstore.backend.infrastructure.persistence.repository;

import com.bookstore.backend.domain.model.author.AuthorModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorModel, Long> {
    
}
