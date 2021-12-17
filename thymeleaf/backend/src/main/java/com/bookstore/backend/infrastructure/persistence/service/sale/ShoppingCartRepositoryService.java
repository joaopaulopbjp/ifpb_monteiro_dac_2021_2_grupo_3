package com.bookstore.backend.infrastructure.persistence.service.sale;

import java.util.NoSuchElementException;

import com.bookstore.backend.domain.model.sale.ShoppingCartModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.repository.sale.ShoppingCartRepository;
import com.bookstore.backend.infrastructure.utils.Utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartRepositoryService {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartRepository getInstance() {
        return shoppingCartRepository;
    }

    public ShoppingCartModel update(ShoppingCartModel shoppingCartModel) throws NotFoundException {
        ShoppingCartModel shoppingCartDB = null;
        try {
            shoppingCartDB = shoppingCartRepository.findById(shoppingCartModel.getId()).get();

        } catch (NoSuchElementException e) {
            throw new NotFoundException();
        }

        BeanUtils.copyProperties(shoppingCartModel, shoppingCartDB, Utils.getNullPropertyNames(shoppingCartModel));

        return shoppingCartRepository.save(shoppingCartDB);
    }
}
