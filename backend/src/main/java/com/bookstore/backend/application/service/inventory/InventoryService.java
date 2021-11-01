package com.bookstore.backend.application.service.inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.bookstore.backend.domain.model.inventory.InventoryModel;
import com.bookstore.backend.domain.model.product.BookModel;
import com.bookstore.backend.domain.model.user.UserModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.service.inventory.InventoryRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.person.UserRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.product.BookRepositoryService;
import com.bookstore.backend.infrastructure.utils.AdminVerify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepositoryService inventoryRepositoryService;

    @Autowired
    private BookRepositoryService bookRepositoryService;

    @Autowired
    private UserRepositoryService userRepositoryService;

    @Autowired
    private AdminVerify adminVerify;

    public InventoryModel update(InventoryModel inventoryModel) throws NotFoundException {
        return inventoryRepositoryService.update(inventoryModel);
    }

    public InventoryModel findById(Long id, String username) throws Exception {
        Optional<InventoryModel> inventory = inventoryRepositoryService.getInstance().findById(id);
        if(inventory == null){
            throw new NotFoundException();
        }
        if(!adminVerify.isAdmin(username)) {
            Optional<UserModel> userOp = userRepositoryService.getInstance().findByUsername(username);
            boolean flag = userOp.get().getProductForSaleList()
                .stream().filter(product -> product.getInventory().getId() == id)
                .findFirst().isPresent();

            if(!flag)
                throw new Exception("You can't get this inventory because it belongs to another user");
        }
        return inventory.get();
    }

    public List<InventoryModel> findAll(String username) {
        List<InventoryModel> inventory = new ArrayList<>();
        if(!adminVerify.isAdmin(username)) {
            Optional<UserModel> userOp = userRepositoryService.getInstance().findByUsername(username);
            inventory = userOp.get().getProductForSaleList().stream()
                .map(product -> product.getInventory())
                .collect(Collectors.toList());
        } else {
            inventory = inventoryRepositoryService.getInstance().findAll();
        }
        return inventory;
    }

    public void incremet(Integer value, Long id, String username) throws Exception {
        Optional<BookModel> book = bookRepositoryService.getInstance().findById(id);
        if(!book.isPresent()){
            throw new NotFoundException("Book with id " + id + " not found");
        }
        if(value <= 0){
            throw new IllegalArgumentException("value must be greater than 0.");
        }

        if(!adminVerify.isAdmin(username)) {
            Optional<UserModel> userOp = userRepositoryService.getInstance().findByUsername(username);
            boolean flag = userOp.get().getProductForSaleList().stream()
                .map(product -> product.getInventory())
                .findFirst().isPresent();
            if(!flag)
                throw new Exception("You can't incremet this inventory because it belongs to another user");
        }
        book.get().getInventory().addAmount(value);
        bookRepositoryService.getInstance().save(book.get());
    }
    
    public void decrement(Integer value, Long id, String username) throws Exception {
        Optional<BookModel> book = bookRepositoryService.getInstance().findById(id);
        if(!book.isPresent()){
            throw new NotFoundException("Book with id " + id + " not found");
        }
        if(value <= 0){
            throw new IllegalArgumentException("value must be greater than 0.");
        }

        if(!adminVerify.isAdmin(username)) {
            Optional<UserModel> userOp = userRepositoryService.getInstance().findByUsername(username);
            boolean flag = userOp.get().getProductForSaleList().stream()
                .map(product -> product.getInventory())
                .findFirst().isPresent();
            if(!flag)
                throw new Exception("You can't incremet this inventory because it belongs to another user");
        }
        book.get().getInventory().removerAmount(value);
        bookRepositoryService.getInstance().save(book.get());
        
    }
    
}
