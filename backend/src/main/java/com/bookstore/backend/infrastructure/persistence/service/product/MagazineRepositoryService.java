package com.bookstore.backend.infrastructure.persistence.service.product;

import java.util.List;
import java.util.NoSuchElementException;

import com.bookstore.backend.domain.model.product.MagazineModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.repository.product.MagazineRepository;
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
public class MagazineRepositoryService {
    @Autowired
    private MagazineRepository magazineRepository;

    @Value("${numberOfItemsPerPage}")
    private String numberOfItemsPerPage;

    public MagazineRepository getInstance() {
        return magazineRepository;
    }

    public List<MagazineModel> findCheapests(int quantity) throws NotFoundException {
        Pageable pageable = PageRequest.of(0, quantity, Sort.by("price").ascending());

        Page<MagazineModel> pages = magazineRepository.findAll(pageable);
        
        if(pages.isEmpty()) throw new NotFoundException();
        
        return pages.getContent();
    }

    public List<MagazineModel> findAll(int pageNumber) throws NotFoundException {
        Pageable pageable = PageRequest.of(pageNumber, Integer.parseInt(numberOfItemsPerPage), Sort.by("title").ascending());
        Page<MagazineModel> pages = magazineRepository.findAll(pageable);

        if(pages.isEmpty()) throw new NotFoundException();
        
        return pages.getContent();
    }

    public MagazineModel update(MagazineModel magazineModel) throws NotFoundException {
        MagazineModel magazineModelDataBase = null;
        try {
            magazineModelDataBase = magazineRepository.findById(magazineModel.getId()).get();

        } catch (NoSuchElementException e) {
            throw new NotFoundException();
        }

        BeanUtils.copyProperties(magazineModel, magazineModelDataBase, Utils.getNullPropertyNames(magazineModel));

        return magazineRepository.save(magazineModel);
    }
}
