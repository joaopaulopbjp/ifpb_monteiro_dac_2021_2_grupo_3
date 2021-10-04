package com.bookstore.backend.infrastructure.persistence.repository.product;

import com.bookstore.backend.domain.model.product.HqModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HqRepository extends JpaRepository<HqModel, Long> {
        
}

