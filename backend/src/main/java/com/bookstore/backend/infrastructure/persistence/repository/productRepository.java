package com.bookstore.backend.infrastructure.persistence.repository;

import com.bookstore.backend.domain.model.product.ProductModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface productRepository extends JpaRepository<ProductModel, Long> {
        
}

