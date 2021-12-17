package com.bookstore.backend.application.controller.sale.revenue;

import java.math.BigDecimal;

import com.bookstore.backend.application.service.sale.revenue.RevenueService;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.presentation.response.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/revenue")
public class RevenueController {
    @Autowired
    private RevenueService revenueService;

    @PostMapping("/calculate-all")
    public ResponseEntity<?> calculateAll() {
        try {
            BigDecimal total = revenueService.calculateAll();
            return ResponseEntity.status(HttpStatus.OK).body(new Response(total.toString()));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getMessage()));
        }
    }
}
