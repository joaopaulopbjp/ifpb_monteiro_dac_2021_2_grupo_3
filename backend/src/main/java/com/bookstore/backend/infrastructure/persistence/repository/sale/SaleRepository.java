package com.bookstore.backend.infrastructure.persistence.repository.sale;

import java.time.LocalDate;
import java.util.List;

import com.bookstore.backend.domain.model.sale.SaleModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<SaleModel, Long> {
    @Query("SELECT sale FROM SaleModel sale WHERE sale.dateSale >= ?1 AND sale.dateSale <= ?2")
    public List<SaleModel> findSaleByDates(LocalDate start, LocalDate end);
}
