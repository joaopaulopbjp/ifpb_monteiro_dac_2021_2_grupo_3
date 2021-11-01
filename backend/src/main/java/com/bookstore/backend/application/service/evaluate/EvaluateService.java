package com.bookstore.backend.application.service.evaluate;

import java.util.List;
import java.util.Optional;

import com.bookstore.backend.domain.model.evaluation.EvaluateModel;
import com.bookstore.backend.domain.model.product.BookModel;
import com.bookstore.backend.domain.model.user.UserModel;
import com.bookstore.backend.infrastructure.exception.FullListException;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.service.evaluate.EvaluateRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.person.UserRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.product.BookRepositoryService;
import com.bookstore.backend.infrastructure.utils.AdminVerify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvaluateService {
    
    @Autowired
    private EvaluateRepositoryService evaluateRepositoryService;

    @Autowired
    private BookRepositoryService bookRepositoryService;

    @Autowired
    private UserRepositoryService userRepositoryService;

    @Autowired
    private AdminVerify adminVerify;

    public EvaluateModel save(EvaluateModel evaluate, Long idBook, String username) throws Exception {
        if(adminVerify.idAdmin(username))
            throw new Exception("You are admin. You can't add evaluate to products");
            
        Optional<BookModel> book = bookRepositoryService.getInstance().findById(idBook);
        if(!book.isPresent()){
            throw new NotFoundException("Not found book " + idBook);
        }
        Optional<UserModel> user = userRepositoryService.getInstance().findByUsername(username);

        if(evaluate.getStarNumber() <= 0 || evaluate.getStarNumber() > 5) {
        	throw new IllegalArgumentException("invalid rating: " + evaluate.getStarNumber());
        }
        if(evaluate.getComment() == null || evaluate.getComment().length() < 4 || evaluate.getComment().length() > 1000) {
        	throw new IllegalArgumentException("amount of invalid characters: " + evaluate.getComment().length());
        }
        evaluate = evaluateRepositoryService.getInstance().save(evaluate);
        book.get().addEvaluateToEvaluateList(evaluate);
        bookRepositoryService.getInstance().save(book.get());
        user.get().addEvaluateToEvaluateList(evaluate);
        userRepositoryService.getInstance().save(user.get());
        return evaluate;
    }

    public void delete(Long evaluateId, String username) throws Exception {
        Optional<BookModel> bookOp = bookRepositoryService.getInstance().findByEvaluateId(evaluateId);
        Optional<EvaluateModel> evaluateOp = evaluateRepositoryService.getInstance().findById(evaluateId);
        Optional<UserModel> userOp = userRepositoryService.getInstance().findByUsername(username);

        if(!evaluateOp.isPresent()){
            throw new NotFoundException("Not found Evaluate " + evaluateId);
        }
        if(!bookOp.isPresent()){
            throw new NotFoundException("Not found Book");
        }

        if(!adminVerify.idAdmin(username)) {
            boolean flag = userOp.get().getEvaluateList()
                .stream().filter(evaluate -> evaluate.getId() == evaluateId)
                .findFirst().isPresent();
            if(!flag)
                throw new Exception("You can't delete this address because it belongs to another user");

        } else {
            userOp = userRepositoryService.getInstance().findByEvaluateId(evaluateId);
        }

        bookOp.get().removeEvaluateFromEvaluateList(evaluateOp.get());
        userOp.get().removeEvaluateFromEvaluateList(evaluateOp.get());
        bookRepositoryService.getInstance().save(bookOp.get());
        userRepositoryService.getInstance().save(userOp.get());
        evaluateRepositoryService.getInstance().deleteById(evaluateId);
    }

    public EvaluateModel update(EvaluateModel evaluate) throws NotFoundException{
        return evaluateRepositoryService.update(evaluate);
    }

    public List<EvaluateModel> findAll() throws NotFoundException{
        List<EvaluateModel> evaluateList = evaluateRepositoryService.getInstance().findAll();
        if(evaluateList.isEmpty()){
            throw new NotFoundException("List is empty");
        }
        return evaluateList;
    }

    public EvaluateModel findById(Long id) throws NotFoundException{
        Optional<EvaluateModel> evaluate = evaluateRepositoryService.getInstance().findById(id);
        if(!evaluate.isPresent()){
            throw new NotFoundException("Not Found evaluate " + id);
        }
        return evaluate.get();
    }
}
