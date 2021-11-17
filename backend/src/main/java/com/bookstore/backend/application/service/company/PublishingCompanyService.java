package com.bookstore.backend.application.service.company;

import java.util.List;
import java.util.Optional;

import com.bookstore.backend.domain.model.company.PublishingCompanyModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.service.company.PublishingCompanyRepositoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublishingCompanyService {

    @Autowired
    private PublishingCompanyRepositoryService publishingCompanyRepositoryService;

    public PublishingCompanyModel save(PublishingCompanyModel publishingCompanyModel) throws IllegalArgumentException{
        if(publishingCompanyModel.getName().isEmpty()){
            throw new IllegalArgumentException("name can't not be Empty");
        }
        return publishingCompanyRepositoryService.getInstance().save(publishingCompanyModel);
    }

    public void delete(Long id) throws NotFoundException, IllegalArgumentException{
        PublishingCompanyModel publishingCompanyModel = publishingCompanyRepositoryService.getInstance().findById(id).get();
        publishingCompanyRepositoryService.getInstance().delete(publishingCompanyModel);
    }

    public PublishingCompanyModel update(PublishingCompanyModel publishingCompanyModel) throws NotFoundException{
        if(publishingCompanyModel.getName().isEmpty()){
            throw new IllegalArgumentException("name can't not be Empty");
        }
        return publishingCompanyRepositoryService.update(publishingCompanyModel);
    }
    
    public PublishingCompanyModel findById(Long id) throws NotFoundException{
        Optional<PublishingCompanyModel> company = publishingCompanyRepositoryService.getInstance().findById(id);
        if(company == null)
            throw new NotFoundException();
        return company.get();
    }

    public List<PublishingCompanyModel> findByName(String name) throws NotFoundException{
        List<PublishingCompanyModel> company = publishingCompanyRepositoryService.getInstance().findByName(name);
        if(company == null){
            throw new NotFoundException();
        }
        return company;
    }

    public List<PublishingCompanyModel> findAll(){
        return publishingCompanyRepositoryService.getInstance().findAll();
    }
}
