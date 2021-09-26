package com.bookstore.backend.domain.model;

import java.math.BigDecimal;

public class Sale {
    private Long id;
    private BookModel book;
    private double amount;

    public BigDecimal getTotalSalesPrice() {
        return book.getPrice().multiply(new BigDecimal(amount));
    }
}
