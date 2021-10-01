package com.bookstore.backend.infrastructure.persistence.repository;

import com.bookstore.backend.domain.model.CategoryModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryModel, Long> {
    
}