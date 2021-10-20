package com.bookstore.backend.application.service.inventory;

import java.util.List;
import java.util.Optional;

import com.bookstore.backend.domain.model.inventory.InventoryModel;
import com.bookstore.backend.domain.model.product.BookModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.service.inventory.InventoryRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.product.BookRepositoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepositoryService inventoryRepositoryService;

    @Autowired
    private BookRepositoryService bookRepositoryService;

    public InventoryModel update(InventoryModel inventoryModel) throws NotFoundException {
        return inventoryRepositoryService.update(inventoryModel);
    }

    public InventoryModel findById(Long id) throws NotFoundException {
        Optional<InventoryModel> inventory = inventoryRepositoryService.getInstance().findById(id);
        if(inventory == null){
            throw new NotFoundException();
        }
        return inventory.get();
    }

    public List<InventoryModel> findAll(){
        return inventoryRepositoryService.getInstance().findAll();
    }

    public void incremet(Integer value, Long id) throws NotFoundException {
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
    
    public void decrement(Integer value, Long id) throws NotFoundException {
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
