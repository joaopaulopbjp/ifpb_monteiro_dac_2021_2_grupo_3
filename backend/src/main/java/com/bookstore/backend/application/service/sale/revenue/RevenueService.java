package com.bookstore.backend.application.service.sale.revenue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.bookstore.backend.domain.model.sale.RevenuesModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.service.sale.RevenuesRepositoryServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class RevenueService {
    
    @Autowired
    private RevenuesRepositoryServices revenuesRepositoryServices;

    @EventListener(ApplicationReadyEvent.class)
    private void init() {
        List<RevenuesModel> revenueList = revenuesRepositoryServices.getInstance().findAll();
        if(revenueList.isEmpty()) {
            RevenuesModel revenues = new RevenuesModel();
            revenuesRepositoryServices.getInstance().save(revenues);
        }
    }

    public BigDecimal calculateAll() throws NotFoundException {
        List<RevenuesModel> revenueList = revenuesRepositoryServices.getInstance().findAll();

        Optional<RevenuesModel> opRevenue = revenueList.stream().findFirst();
        if(!opRevenue.isPresent())
            throw new NotFoundException("Database don't have any sale");
        
        BigDecimal total = opRevenue.get().calculateAll();
        return total;
    }
}
