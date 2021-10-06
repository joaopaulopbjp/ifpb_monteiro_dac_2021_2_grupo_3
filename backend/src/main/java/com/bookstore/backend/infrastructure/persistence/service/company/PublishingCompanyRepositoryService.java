package com.bookstore.backend.infrastructure.persistence.service.company;

import java.util.NoSuchElementException;

import com.bookstore.backend.domain.model.company.PublishingCompanyModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.repository.company.PublishingCompanyRepository;
import com.bookstore.backend.infrastructure.utils.Utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class PublishingCompanyRepositoryService {
    @Autowired
    private PublishingCompanyRepository publishingCompanyRepository;

    public PublishingCompanyRepository getInstance() {
        return publishingCompanyRepository;
    }

    public PublishingCompanyModel update(PublishingCompanyModel publishingCompanyModel) throws NotFoundException {
        PublishingCompanyModel companyDB = null;
        try {
            companyDB = publishingCompanyRepository.findById(publishingCompanyModel.getId()).get();

        } catch (NoSuchElementException e) {
            throw new NotFoundException();
        }

        BeanUtils.copyProperties(publishingCompanyModel, companyDB, Utils.getNullPropertyNames(publishingCompanyModel));

        return publishingCompanyRepository.save(companyDB);
    }
}
