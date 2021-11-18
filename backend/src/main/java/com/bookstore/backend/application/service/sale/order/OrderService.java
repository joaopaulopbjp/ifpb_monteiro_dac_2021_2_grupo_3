package com.bookstore.backend.application.service.sale.order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bookstore.backend.application.service.sale.shoppingCart.ShoppingCartService;
import com.bookstore.backend.domain.model.sale.ItemOrderModel;
import com.bookstore.backend.domain.model.sale.OrderModel;
import com.bookstore.backend.domain.model.sale.RevenuesModel;
import com.bookstore.backend.domain.model.sale.SaleModel;
import com.bookstore.backend.domain.model.sale.ShoppingCartModel;
import com.bookstore.backend.domain.model.sale.UserSaleHistoryModel;
import com.bookstore.backend.domain.model.user.UserModel;
import com.bookstore.backend.infrastructure.enumerator.orderModel.OrderStatus;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.service.person.UserRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.sale.OrderRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.sale.RevenuesRepositoryServices;
import com.bookstore.backend.infrastructure.persistence.service.sale.SaleRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.sale.ShoppingCartRepositoryService;
import com.bookstore.backend.infrastructure.utils.AdminVerify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepositoryService orderRepositoryService;

    @Autowired
    private UserRepositoryService userRepositoryService;

    @Autowired
    private SaleRepositoryService saleRepositoryService;

    @Autowired
    private RevenuesRepositoryServices revenuesRepositoryServices;

    @Autowired
    private AdminVerify adminVerify;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ShoppingCartRepositoryService shoppingCartRepositoryService;

    public OrderModel save(String username) throws Exception {
        if(adminVerify.isAdmin(username))
            throw new Exception("You can't save an order because you are an admin");

        OrderModel order = new OrderModel();

        List<ItemOrderModel> itemList = new ArrayList<>();
        ShoppingCartModel shoppingCart = shoppingCartService.findShoppingCart(username);
        for(ItemOrderModel item : shoppingCart.getItemList()) {
            itemList.add(item);
        }
        
        if(itemList.isEmpty())
            throw new Exception("You can't realize an order with shopping cart empty");

        Optional<UserModel> user = userRepositoryService.getInstance().findByUsername(username);

        if(user.get().getSaleHistory()==null){
            user.get().setSaleHistory(new UserSaleHistoryModel());
        }
        
        order.setDateOrder(LocalDate.now());
        order.setStatus(OrderStatus.PROCESSING);
        order.setItemList(itemList);
        order = orderRepositoryService.getInstance().save(order);
        user.get().getSaleHistory().addOrderToOrderList(order);
        userRepositoryService.getInstance().save(user.get());

        shoppingCart.getItemList().clear();
        shoppingCart.setTotalPrice(new BigDecimal(0));
        shoppingCartRepositoryService.getInstance().save(shoppingCart);

        List<SaleModel> saleList = new ArrayList<>();
        Optional<SaleModel> saleOp = null;
        for(ItemOrderModel itemOrder : order.getItemList()) {
            saleOp = saleRepositoryService.getInstance().findByProductId(itemOrder.getProduct().getId());

            if(!saleOp.isPresent()) {
                SaleModel sale = new SaleModel(0l, itemOrder.getProduct(), itemOrder.getAmount());
                saleList.add(sale);
            } else {
                saleOp.get().incress(itemOrder.getAmount());
                saleList.add(saleOp.get());
            }
        }
        saleList = saleRepositoryService.getInstance().saveAll(saleList);
        RevenuesModel revenue = revenuesRepositoryServices.getInstance().findAll().stream().findFirst().get();
        for(SaleModel sale : saleList) {
            revenue.addSaleToSaleList(sale);
        }
        revenuesRepositoryServices.getInstance().save(revenue);
        return order;
    }

    public OrderModel findById(Long id, String username) throws NotFoundException, Exception {
        Optional<OrderModel> order = orderRepositoryService.getInstance().findById(id);
        if(!order.isPresent()){
            throw new NotFoundException("Not Found Order " + id);
        }

        if(!adminVerify.isAdmin(username)) {
            Optional<UserModel> userOp = userRepositoryService.getInstance().findByUsername(username);
            boolean flag = userOp.get().getSaleHistory().getOrderList()
                .stream().filter(orderStream -> orderStream.getId() == id)
                .findFirst().isPresent();
            
            if(!flag)
                throw new Exception("Order with id " + id + " belong to another user");
        }
        return order.get();
    }

    public List<OrderModel> findAll(String username) throws NotFoundException, Exception {
        if(!adminVerify.isAdmin(username))
            throw new Exception("You can't find all orders because you are an user");

        List<OrderModel> orderList = orderRepositoryService.getInstance().findAll();
        if(orderList.isEmpty()){
            throw new NotFoundException();
        }
        return orderList;
    }

    public OrderModel updateStatus(Long idOrder, Long idStatus, String username) throws IllegalArgumentException, NotFoundException, Exception {
        Optional<OrderModel> order = orderRepositoryService.getInstance().findById(idOrder);
        if(!order.isPresent()){
            throw new NotFoundException("Not found Order " + idOrder);
        }

        if(!adminVerify.isAdmin(username)) {
            Optional<UserModel> userOp = userRepositoryService.getInstance().findByUsername(username);
            boolean flag = userOp.get().getSaleHistory().getOrderList()
                .stream().filter(orderStream -> orderStream.getId() == idOrder)
                .findFirst().isPresent();
            
            if(!flag)
                throw new Exception("Order with id " + idOrder + " belong to another user");
        }
        
        OrderStatus status = OrderStatus.getById(idStatus);
        order.get().setStatus(status);
        return orderRepositoryService.getInstance().save(order.get());
    }
}
