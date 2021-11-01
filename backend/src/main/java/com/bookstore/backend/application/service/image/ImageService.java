package com.bookstore.backend.application.service.image;

import java.util.List;
import java.util.Optional;

import com.bookstore.backend.domain.model.image.ImageModel;
import com.bookstore.backend.domain.model.product.BookModel;
import com.bookstore.backend.domain.model.user.UserModel;
import com.bookstore.backend.infrastructure.exception.FullListException;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.service.image.ImageRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.person.UserRepositoryService;
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
    private UserRepositoryService userRepositoryService;

    @Autowired
    private AdminVerify adminVerify;

    public ImageModel save(ImageModel image, Long idBook, String username) throws FullListException, NotFoundException, Exception{
        if(!adminVerify.idAdmin(username)){

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
        if(!adminVerify.idAdmin(username)){
            Optional<UserModel> userOp = userRepositoryService.getInstance().findByUsername(username);
            boolean flag = userOp.get().getProductForSaleList().stream().map(product -> product.getImageList())
                .map(imageList -> imageList.stream()
                .filter(imageVerify -> imageVerify.getId() == id))
                .findFirst().isPresent();
            if(!flag){
                throw new Exception("You can't delete this image because it belongs to another user");
            }

        }
        imageRepositoryService.getInstance().deleteById(id);
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
