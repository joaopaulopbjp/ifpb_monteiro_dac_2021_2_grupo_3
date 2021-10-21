package com.bookstore.backend.application.controller.sale.order;

import com.bookstore.backend.application.service.sale.order.OrderService;
import com.bookstore.backend.domain.model.sale.OrderModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.modelmapper.ModelMapperService;
import com.bookstore.backend.presentation.dto.sale.OrderDTO;
import com.bookstore.backend.presentation.response.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ap/order")
@CrossOrigin
public class OrderController {
    
    @Autowired
    private OrderService orderService; 
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody OrderDTO dto){
        try {
            OrderModel order = (OrderModel) ModelMapperService.convertToModel(dto, OrderModel.class);
            orderService.save(order, dto.getIdItemList(), dto.getIdUser());
            dto = (OrderDTO) ModelMapperService.convertToDTO(order, OrderDTO.class);
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getMessage()));
        } 
    }
}
