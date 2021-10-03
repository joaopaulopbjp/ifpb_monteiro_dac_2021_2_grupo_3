package com.bookstore.backend.infrastructure.persistence.repository;

import com.bookstore.backend.domain.model.sale.SaleModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<SaleModel, Long> {
    
}
