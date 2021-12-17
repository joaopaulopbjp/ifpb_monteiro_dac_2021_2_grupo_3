package com.bookstore.backend.infrastructure.persistence.service.image;

import java.util.NoSuchElementException;

import com.bookstore.backend.domain.model.image.ImageModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.repository.image.ImageRepository;
import com.bookstore.backend.infrastructure.utils.Utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageRepositoryService {
    
    @Autowired
    private ImageRepository imageRepository;

    public ImageRepository getInstance(){
        return imageRepository;
    }

    public ImageModel update(ImageModel imageModel) throws NotFoundException {
        ImageModel imageDB = null;
        try {
            imageDB = imageRepository.findById(imageModel.getId()).get();

        } catch (NoSuchElementException e) {
            throw new NotFoundException();
        }

        BeanUtils.copyProperties(imageModel, imageDB, Utils.getNullPropertyNames(imageModel));

        return imageRepository.save(imageDB);
    } 
}
