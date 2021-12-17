package com.bookstore.backend.infrastructure.persistence.repository.sale;

import java.util.Optional;

import com.bookstore.backend.domain.model.sale.SaleModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<SaleModel, Long> {
    @Query(value = "SELECT * FROM t_sale WHERE product_fk = :productId", nativeQuery = true)
    public Optional<SaleModel> findByProductId(@Param("productId") Long productId);
}
