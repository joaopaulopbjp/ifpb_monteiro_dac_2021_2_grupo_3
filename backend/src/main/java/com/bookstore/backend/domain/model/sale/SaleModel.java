package com.bookstore.backend.domain.model.sale;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.bookstore.backend.domain.model.product.ProductModel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "T_SALE")
public class SaleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PRODUCT_FK")
    private ProductModel product;

    @Column(name = "DATA_SALE")
	private LocalDate dateSale;

    @Column(name = "AMOUNT")
    private Integer amount;

    @ManyToOne
    @JoinColumn(name = "REVENUE_FK", nullable = false)
    private RevenuesModel revenues; 

    public BigDecimal getTotalSalesPrice() {
        return product.getPrice().multiply(new BigDecimal(amount));
    }

    @Override
    public String toString() {
        return String.format("SALE [ID: %s - AMOUNT: %s ]", getId(), getAmount());
    }
}
