package com.bookstore.backend.infrastructure.persistence.service.inventory;

import java.util.NoSuchElementException;

import com.bookstore.backend.domain.model.inventory.InventoryModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.repository.inventory.InventoryRepository;
import com.bookstore.backend.infrastructure.utils.Utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryRepositoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    public InventoryRepository getInstance() {
        return inventoryRepository;
    }

    public InventoryModel update(InventoryModel inventoryModel) throws NotFoundException {
        InventoryModel inventoryDB = null;
        try {
            inventoryDB = inventoryRepository.findById(inventoryModel.getId()).get();

        } catch (NoSuchElementException e) {
            throw new NotFoundException();
        }

        BeanUtils.copyProperties(inventoryModel, inventoryDB, Utils.getNullPropertyNames(inventoryModel));

        return inventoryRepository.save(inventoryDB);
    } 
}
