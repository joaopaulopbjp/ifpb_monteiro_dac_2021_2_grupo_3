package com.bookstore.backend.domain.model.sale;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

public class RevenuesModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long Id;

    @OneToMany
    private List<SaleModel> sales;

    public BigDecimal calculate(LocalDate start, LocalDate end) {

    }
}
