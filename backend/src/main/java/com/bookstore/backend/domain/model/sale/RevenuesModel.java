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
    @Column(name = "ID")
    private long Id;

    @OneToMany(mappedBy = "revenues", fetch = FetchType.EAGER)
    private List<SaleModel> saleList;


    public BigDecimal calculate(List<SaleModel> list) {
        BigDecimal revenue = new BigDecimal("0");

        for(SaleModel sale : list) {
            revenue = revenue.add(sale.getTotalSalesPrice());
        }
        return revenue;
    }
}
