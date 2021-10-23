package com.bookstore.backend.application.service.sale.revenue;

import com.bookstore.backend.BackendApplication;
import com.bookstore.backend.infrastructure.persistence.service.sale.RevenuesRepositoryServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class RevenueService {
    
    @Autowired
    private RevenuesRepositoryServices revenuesRepositoryServices;

    @EventListener(BackendApplication.class)
    public static void init() {
        System.out.println("TEU PAI AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
    }

}
