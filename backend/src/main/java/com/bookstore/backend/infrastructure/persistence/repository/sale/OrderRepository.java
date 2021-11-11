package com.bookstore.backend.infrastructure.persistence.repository.sale;

import java.time.LocalDate;
import java.util.List;

import com.bookstore.backend.domain.model.sale.OrderModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel, Long> {
    @Query("SELECT order FROM OrderModel order WHERE order.dateOrder >= ?1 AND order.dateOrder <= ?2")
    public List<OrderModel> findOrderByDates(LocalDate start, LocalDate end);
}
