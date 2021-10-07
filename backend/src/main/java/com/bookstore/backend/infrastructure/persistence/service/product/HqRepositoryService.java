package com.bookstore.backend.infrastructure.persistence.service.product;

import java.util.List;
import java.util.NoSuchElementException;

import com.bookstore.backend.domain.model.product.HqModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.repository.product.HqRepository;
import com.bookstore.backend.infrastructure.utils.Utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class HqRepositoryService {
    @Autowired
    private HqRepository hqRepository;

    @Value("${numberOfItemsPerPage}")
    private String numberOfItemsPerPage;

    public HqRepository getInstance() {
        return hqRepository;
    }

    public List<HqModel> findCheapests(int quantity) throws NotFoundException {
        Pageable pageable = PageRequest.of(0, quantity, Sort.by("price").ascending());

        Page<HqModel> pages = hqRepository.findAll(pageable);
        
        if(pages.isEmpty()) throw new NotFoundException();
        
        return pages.getContent();
    }

    public List<HqModel> findAll(int pageNumber) throws NotFoundException {
        Pageable pageable = PageRequest.of(pageNumber, Integer.parseInt(numberOfItemsPerPage), Sort.by("title").ascending());
        Page<HqModel> pages = hqRepository.findAll(pageable);

        if(pages.isEmpty()) throw new NotFoundException();
        
        return pages.getContent();
    }

    public HqModel update(HqModel hqModel) throws NotFoundException {
        HqModel hqModelDataBase = null;
        try {
            hqModelDataBase = hqRepository.findById(hqModel.getId()).get();

        } catch (NoSuchElementException e) {
            throw new NotFoundException();
        }

        BeanUtils.copyProperties(hqModel, hqModelDataBase, Utils.getNullPropertyNames(hqModel));

        return hqRepository.save(hqModelDataBase);
    }
}
