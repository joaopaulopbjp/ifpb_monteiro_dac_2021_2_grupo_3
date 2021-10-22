package com.bookstore.backend.application.service.image;

import java.util.List;
import java.util.Optional;

import com.bookstore.backend.domain.model.image.ImageModel;
import com.bookstore.backend.domain.model.product.BookModel;
import com.bookstore.backend.infrastructure.exception.FullListException;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.service.image.ImageRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.product.BookRepositoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    
    @Autowired
    private ImageRepositoryService imageRepositoryService;

    @Autowired
    private BookRepositoryService bookRepositoryService;

    public ImageModel save(ImageModel image, Long idBook) throws FullListException, NotFoundException{
        Optional<BookModel> book = bookRepositoryService.getInstance().findById(idBook);
        if(!book.isPresent()){
            throw new NotFoundException("Not Found Book " + idBook);
        }
        book.get().addImageToImageList(image);
        BookModel bookSaved = bookRepositoryService.getInstance().save(book.get());
        image = bookSaved.findImageByContent(image.getBase64());
        return image;
    }

    public void delete(Long id) throws NotFoundException{
        Optional<ImageModel> image = imageRepositoryService.getInstance().findById(id);
        if(!image.isPresent()){
            throw new NotFoundException("Not found image " + id);
        }else {
            imageRepositoryService.getInstance().deleteById(id);
        }
    }

    public List<ImageModel> findAll() throws NotFoundException{
        List<ImageModel> imageList = imageRepositoryService.getInstance().findAll();
        if(imageList.isEmpty()){
            throw new NotFoundException("List is empty");
        }
        return imageList;
    }

    public ImageModel findById(Long id) throws NotFoundException{
        Optional<ImageModel> image = imageRepositoryService.getInstance().findById(id);
        if(!image.isPresent()){
            throw new NotFoundException("Not found image" + id);
        }
        return image.get();
    }
}
