package com.bookstore.backend.infrastructure.persistence.service.product;

import java.util.List;
import com.bookstore.backend.domain.model.product.ProductModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductRepositoryService {
    @Autowired
    private ProductRepository productRepository;

    @Value("${numberOfItemsPerPage}")
    private String numberOfItemsPerPage;

    public ProductRepository getInstance() {
        return productRepository;
    }

    public List<ProductModel> findCheapests(int quantity) throws NotFoundException {
        Pageable pageable = PageRequest.of(0, quantity, Sort.by("price").ascending());

        Page<ProductModel> pages = productRepository.findAllIgnoreInventoryUnavailable(pageable);
        
        if(pages.isEmpty()) throw new NotFoundException();
        
        return pages.getContent();
    }

    public List<ProductModel> findAll(int pageNumber) throws NotFoundException {
        Pageable pageable = PageRequest.of(pageNumber, Integer.parseInt(numberOfItemsPerPage), Sort.by("title").ascending());
        Page<ProductModel> pages = productRepository.findAll(pageable);

        if(pages.isEmpty()) throw new NotFoundException();
        
        return pages.getContent();
    }
}
