package com.bookstore.backend.infrastructure.persistence.service.sale;

import java.util.NoSuchElementException;

import com.bookstore.backend.domain.model.sale.SaleModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.repository.sale.SaleRepository;
import com.bookstore.backend.infrastructure.utils.Utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class SaleRepositoryService {
    @Autowired
    private SaleRepository saleRepository;

    public SaleRepository getInstace() {
        return saleRepository;
    }

    public SaleModel update(SaleModel saleModel) throws NotFoundException {
        SaleModel saleDB = null;
        try {
            saleDB = saleRepository.findById(saleModel.getId()).get();

        } catch (NoSuchElementException e) {
            throw new NotFoundException();
        }

        BeanUtils.copyProperties(saleModel, saleDB, Utils.getNullPropertyNames(saleModel));

        return saleRepository.save(saleDB);
    }
}
