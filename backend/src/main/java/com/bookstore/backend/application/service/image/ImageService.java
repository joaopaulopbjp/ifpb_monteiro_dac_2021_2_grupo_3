package com.bookstore.backend.application.service.image;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.bookstore.backend.domain.model.image.ImageModel;
import com.bookstore.backend.domain.model.product.BookModel;
import com.bookstore.backend.domain.model.product.ProductModel;
import com.bookstore.backend.domain.model.user.AdminModel;
import com.bookstore.backend.infrastructure.exception.FullListException;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.service.image.ImageRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.person.AdminRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.product.BookRepositoryService;
import com.bookstore.backend.infrastructure.utils.AdminVerify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    
    @Autowired
    private ImageRepositoryService imageRepositoryService;

    @Autowired
    private BookRepositoryService bookRepositoryService;

    @Autowired
    private AdminRepositoryService adminRepositoryService;

    @Autowired
    private AdminVerify adminVerify;

    public ImageModel save(ImageModel image, Long idBook, String username) throws FullListException, NotFoundException, Exception{
        if(!adminVerify.isAdmin(username)){

            Optional<BookModel> book = bookRepositoryService.getInstance().findById(idBook);
            if(!book.isPresent()){
                throw new NotFoundException("Not Found Book " + idBook);
            }
            book.get().addImageToImageList(image);
            BookModel bookSaved = bookRepositoryService.getInstance().save(book.get());
            image = bookSaved.findImageByContent(image.getBase64());
            return image;
        }
        throw new Exception("Admins cannot save images");
    }

    public void delete(Long id, String username) throws Exception{
        Optional<ImageModel> image = imageRepositoryService.getInstance().findById(id);
        if(!image.isPresent()){
            throw new NotFoundException("Not found image " + id);
        }

        imageRepositoryService.getInstance().deleteById(id);
    }

    public List<ImageModel> findAll(String username) throws NotFoundException, Exception{
        List<ImageModel> allImageList = imageRepositoryService.getInstance().findAll();
        if(!allImageList.isEmpty()){
            throw new NotFoundException("You don't have any Image in yours product");
        }
        Optional<AdminModel> adminOp = adminRepositoryService.getInstance().findByUsername(username);
        allImageList = new ArrayList<>();

        for(ProductModel productList : adminOp.get().getProductForSaleList()) {
            for(ImageModel imageFor : productList.getImageList()) {
                allImageList.add(imageFor);
            }
        }

        if(allImageList.isEmpty())
            throw new NotFoundException("Image list empty");

        return allImageList;
    }

    public ImageModel findById(Long id, String username) throws Exception{
        Optional<ImageModel> image = imageRepositoryService.getInstance().findById(id);
        if(!image.isPresent()){
            throw new NotFoundException("Not found image " + id);
        }
        return image.get();
    }
}
