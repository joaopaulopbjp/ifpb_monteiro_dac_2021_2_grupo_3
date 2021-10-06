package com.bookstore.backend.infrastructure.persistence.service.sale;

import java.util.NoSuchElementException;

import com.bookstore.backend.domain.model.sale.ItemOrderModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.repository.sale.ItemOrderRepository;
import com.bookstore.backend.infrastructure.utils.Utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class ItemOrderRepositoryService {
    @Autowired
    private ItemOrderRepository itemOrderRepository;

    public ItemOrderRepository getInstace() {
        return itemOrderRepository;
    }

    public ItemOrderModel update(ItemOrderModel itemOrder) throws NotFoundException {
        ItemOrderModel itemOrderDataBase = null;
        try {
            itemOrderDataBase = itemOrderRepository.findById(itemOrder.getId()).get();

        } catch (NoSuchElementException e) {
            throw new NotFoundException();
        }

        BeanUtils.copyProperties(itemOrder, itemOrderDataBase, Utils.getNullPropertyNames(itemOrder));

        return itemOrderRepository.save(itemOrderDataBase);
    }
}
