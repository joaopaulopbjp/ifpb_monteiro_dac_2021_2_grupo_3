package com.bookstore.backend.application.service.inventory;

import java.util.List;
import java.util.Optional;

import com.bookstore.backend.domain.model.inventory.InventoryModel;
import com.bookstore.backend.domain.model.product.BookModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.service.inventory.InventoryRepositoryService;
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
    private AdminVerify adminVerify;

    public InventoryModel update(InventoryModel inventoryModel) throws NotFoundException {
        return inventoryRepositoryService.update(inventoryModel);
    }

    public InventoryModel findById(Long id) throws Exception {
        Optional<InventoryModel> inventory = inventoryRepositoryService.getInstance().findById(id);
        if(inventory == null){
            throw new NotFoundException();
        }

        return inventory.get();
    }

    public List<InventoryModel> findAll() {
        return inventoryRepositoryService.getInstance().findAll();
    }

    public void incremet(Integer value, Long id, String username) throws Exception {
    	if(!adminVerify.isAdmin(username)) {
    		throw new Exception("You cannot increment an inventory because you are a user");
    	}
        Optional<BookModel> book = bookRepositoryService.getInstance().findById(id);
        if(!book.isPresent()){
            throw new NotFoundException("Book with id " + id + " not found");
        }
        if(value <= 0){
            throw new IllegalArgumentException("value must be greater than 0.");
        }

        book.get().getInventory().addAmount(value);
        bookRepositoryService.getInstance().save(book.get());
    }
    
    public void decrement(Integer value, Long id, String username) throws Exception {
    	if(!adminVerify.isAdmin(username)) {
    		throw new Exception("You cannot decrement an inventory because you are a user");
    	}
        Optional<BookModel> book = bookRepositoryService.getInstance().findById(id);
        if(!book.isPresent()){
            throw new NotFoundException("Book with id " + id + " not found");
        }
        if(value <= 0){
            throw new IllegalArgumentException("value must be greater than 0.");
        }

        book.get().getInventory().removerAmount(value);
        bookRepositoryService.getInstance().save(book.get());
        
    }
    
}
