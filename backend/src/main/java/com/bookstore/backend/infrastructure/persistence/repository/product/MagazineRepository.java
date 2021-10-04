package com.bookstore.backend.infrastructure.persistence.repository.product;

import com.bookstore.backend.domain.model.product.MagazineModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MagazineRepository extends JpaRepository<MagazineModel, Long> {
        
}

