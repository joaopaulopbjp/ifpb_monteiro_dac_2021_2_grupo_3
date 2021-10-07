package com.bookstore.backend.infrastructure.persistence.repository.product;

import com.bookstore.backend.domain.model.product.ProductModel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ProductRepository extends JpaRepository<ProductModel, Long> {
    @Query("SELECT product FROM ProductModel product WHERE product.inventory.amount > 0")
    public Page<ProductModel> findAllIgnoreInventoryUnavailable(Pageable pageable);
}

