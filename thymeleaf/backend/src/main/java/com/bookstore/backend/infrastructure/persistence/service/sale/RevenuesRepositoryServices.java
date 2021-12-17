package com.bookstore.backend.infrastructure.persistence.service.sale;

import com.bookstore.backend.infrastructure.persistence.repository.sale.RevenuesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RevenuesRepositoryServices {
    @Autowired
    private RevenuesRepository revenuesRepository;

    public RevenuesRepository getInstance() {
        return revenuesRepository;
    }
}
