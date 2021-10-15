package com.bookstore.backend.domain.model.sale;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
@Table(name = "T_REVENUES")
public class RevenuesModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long Id;

    @JsonBackReference
    @OneToMany(mappedBy = "revenues", fetch = FetchType.EAGER)
    @Column(name = "SALE_LIST_FK")
    private List<SaleModel> saleList;


    public BigDecimal calculate(List<SaleModel> list) {
        BigDecimal revenue = new BigDecimal("0");

        for(SaleModel sale : list) {
            revenue = revenue.add(sale.getTotalSalesPrice());
        }
        return revenue;
    }
}
