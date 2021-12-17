package com.bookstore.backend.infrastructure.persistence.service.sale;

import java.util.NoSuchElementException;

import com.bookstore.backend.domain.model.sale.OrderModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.repository.sale.OrderRepository;
import com.bookstore.backend.infrastructure.utils.Utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderRepositoryService {
    @Autowired
    private OrderRepository orderRepository;

    public OrderRepository getInstance() {
        return orderRepository;
    }

    public OrderModel update(OrderModel order) throws NotFoundException {
        OrderModel orderDB = null;
        try {
            orderDB = orderRepository.findById(order.getId()).get();

        } catch (NoSuchElementException e) {
            throw new NotFoundException();
        }

        BeanUtils.copyProperties(order, orderDB, Utils.getNullPropertyNames(order));

        return orderRepository.save(orderDB);
    }
}
