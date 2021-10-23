package com.bookstore.backend.application.service.sale.order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bookstore.backend.domain.model.product.ProductModel;
import com.bookstore.backend.domain.model.sale.ItemOrderModel;
import com.bookstore.backend.domain.model.sale.OrderModel;
import com.bookstore.backend.domain.model.sale.SaleModel;
import com.bookstore.backend.domain.model.sale.UserSaleHistoryModel;
import com.bookstore.backend.domain.model.user.UserModel;
import com.bookstore.backend.infrastructure.enumerator.orderModel.OrderStatus;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.service.person.UserRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.product.BookRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.sale.ItemOrderRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.sale.OrderRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.sale.SaleRepositoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepositoryService orderRepositoryService;

    @Autowired
    private ItemOrderRepositoryService itemOrderRepositoryService;

    @Autowired
    private UserRepositoryService userRepositoryService;

    @Autowired
    private SaleRepositoryService saleRepositoryService;

    @Autowired
    private BookRepositoryService bookRepositoryService;

    public OrderModel save(OrderModel order, List<Long> idItemList, Long idUser) throws NotFoundException{
        Optional<UserModel> user = userRepositoryService.getInstance().findById(idUser);
        
        if(!user.isPresent()){
            throw new NotFoundException("Not found user " + idUser);
        }
        if(user.get().getSaleHistory()==null){
            user.get().setSaleHistory(new UserSaleHistoryModel());
        }
        for(Long id:idItemList){
            Optional<ItemOrderModel> item = itemOrderRepositoryService.getInstance().findById(id);
            if(!item.isPresent()){
                throw new NotFoundException("Not found itemOrder " + id);
            }
            order.addItemToItemList(item.get());
        }
        order.setDateOrder(LocalDate.now());
        order.setStatus(OrderStatus.PROCESSING);
        order = orderRepositoryService.getInstance().save(order);
        user.get().getSaleHistory().addOrderToOrderList(order);
        userRepositoryService.getInstance().save(user.get());

        List<SaleModel> saleList = new ArrayList<>();
        Optional<SaleModel> saleOp = null;
        for(ItemOrderModel itemOrder : order.getItemList()) {
            saleOp = saleRepositoryService.getInstance().findByProductId(itemOrder.getProduct().getId());

            if(!saleOp.isPresent()) {
                SaleModel sale = new SaleModel(0l, itemOrder.getProduct(), 1);
                saleList.add(sale);
            } else {
                saleOp.get().incressOne();
                saleList.add(saleOp.get());
            }
        }
        saleRepositoryService.getInstance().saveAll(saleList);
        return order;
    }

    public OrderModel updateStatus(Long idOrder, Long idStatus) throws IllegalArgumentException, NotFoundException{
        Optional<OrderModel> order = orderRepositoryService.getInstance().findById(idOrder);
        if(!order.isPresent()){
            throw new NotFoundException("Not found Order " + idOrder);
        }
        
        OrderStatus status = OrderStatus.getById(idStatus);
        order.get().setStatus(status);
        return orderRepositoryService.getInstance().save(order.get());
    }
}
