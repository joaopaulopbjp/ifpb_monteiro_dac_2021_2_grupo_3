package com.bookstore.backend.application.service.sale.shoppingCart;

import java.util.Optional;

import com.bookstore.backend.domain.model.inventory.InventoryModel;
import com.bookstore.backend.domain.model.product.BookModel;
import com.bookstore.backend.domain.model.sale.ItemOrderModel;
import com.bookstore.backend.domain.model.sale.ShoppingCartModel;
import com.bookstore.backend.domain.model.user.UserModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.service.inventory.InventoryRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.person.UserRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.product.BookRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.sale.ItemOrderRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.sale.ShoppingCartRepositoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {
    
    @Autowired
    private UserRepositoryService userRepositoryService;

    @Autowired
    private BookRepositoryService bookRepositoryService;

    @Autowired
    private ItemOrderRepositoryService itemOrderRepositoryService;

    @Autowired
    private ShoppingCartRepositoryService shoppingCartRepositoryService;

    @Autowired
    private InventoryRepositoryService inventoryRepositoryService;

    public ShoppingCartModel add(ShoppingCartModel shoppingCart, Long idProduct, String username) throws NotFoundException, Exception{

        Optional<UserModel> opPerson = userRepositoryService.getInstance().findByUsername(username);
        Optional<BookModel> opBook = bookRepositoryService.getInstance().findById(idProduct);
        Optional<InventoryModel> opInventory = inventoryRepositoryService.getInstance().findByProductId(idProduct);

        if(!opPerson.isPresent())
            throw new NotFoundException("User " + username + " not found");
        if(!opBook.isPresent())
            throw new NotFoundException("Book with id " + idProduct + " not found");
        if(shoppingCart.getItemList().get(0).getAmount() < 1)
            throw new IllegalArgumentException("Amount needed must be greater than 0");
        if(opInventory.get().getAmount() == 0)
            throw new NotFoundException("Inventory unavailable for this product");
        if(shoppingCart.getItemList().get(0).getAmount() > opInventory.get().getAmount())
            throw new IllegalArgumentException("Only " + opInventory.get().getAmount() + " items for sale");

        for(ItemOrderModel itemOrder : opPerson.get().getShoppingCart().getItemList()) {
            if(itemOrder.getProduct().equals(opBook.get()))
                throw new IllegalArgumentException("This item order is present");
        }

        ItemOrderModel itemOrder = shoppingCart.getItemList().get(0);
        itemOrder.setProduct(opBook.get());
        itemOrder = itemOrderRepositoryService.getInstance().save(itemOrder);

        opPerson.get().getShoppingCart().addItemOrderToItemList(itemOrder);

        UserModel Op = userRepositoryService.getInstance().save(opPerson.get());
        return Op.getShoppingCart();
    }

    public ShoppingCartModel remove(Long itemOrderId, String username) throws NotFoundException, Exception {
        
        Optional<UserModel> opPerson = userRepositoryService.getInstance().findByUsername(username);
        Optional<ItemOrderModel> opItemOrder = itemOrderRepositoryService.getInstance().findById(itemOrderId);

        if(!opPerson.isPresent())
            throw new NotFoundException("User " + username + " not found");
        if(!opItemOrder.isPresent())
            throw new NotFoundException("Item order with id " + itemOrderId + " not found");
        
        opPerson.get().getShoppingCart().removeItemOrderFromItemList(opItemOrder.get());
        ShoppingCartModel shopping = shoppingCartRepositoryService.getInstance().save(opPerson.get().getShoppingCart());
        itemOrderRepositoryService.getInstance().delete(opItemOrder.get());
        return shopping;
    }

    public ShoppingCartModel removeAll(String username) throws NotFoundException, Exception {
    
        Optional<UserModel> opPerson = userRepositoryService.getInstance().findByUsername(username);

        if(!opPerson.isPresent())
            throw new NotFoundException("User " + username + " not found");
        
        for(ItemOrderModel itemOrder : opPerson.get().getShoppingCart().getItemList()) {
            itemOrderRepositoryService.getInstance().delete(itemOrder);
        }
        opPerson.get().getShoppingCart().getItemList().clear();
        ShoppingCartModel shopping = shoppingCartRepositoryService.getInstance().save(opPerson.get().getShoppingCart());
        return shopping;
    }

    public ShoppingCartModel findShoppingCart(String username) throws NotFoundException, Exception {
        
        Optional<UserModel> userOp = userRepositoryService.getInstance().findByUsername(username);

        return userOp.get().getShoppingCart();
    }
}
