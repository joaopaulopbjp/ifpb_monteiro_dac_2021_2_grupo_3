package com.bookstore.backend.infrastructure.persistence.service.sale;

import java.util.NoSuchElementException;

import com.bookstore.backend.domain.model.sale.shoppingCartModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.repository.sale.ShoppingCartRepository;
import com.bookstore.backend.infrastructure.utils.Utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class ShoppingCartRepositoryService {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartRepository getInstace() {
        return shoppingCartRepository;
    }

    public shoppingCartModel update(shoppingCartModel shoppingCartModel) throws NotFoundException {
        shoppingCartModel shoppingCartDB = null;
        try {
            shoppingCartDB = shoppingCartRepository.findById(shoppingCartModel.getId()).get();

        } catch (NoSuchElementException e) {
            throw new NotFoundException();
        }

        BeanUtils.copyProperties(shoppingCartModel, shoppingCartDB, Utils.getNullPropertyNames(shoppingCartModel));

        return shoppingCartRepository.save(shoppingCartDB);
    }
}
