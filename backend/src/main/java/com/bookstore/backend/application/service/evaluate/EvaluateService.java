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

    public EvaluateModel save(EvaluateModel evaluate, Long idBook, Long idUser) throws NotFoundException, FullListException{
        Optional<BookModel> book = bookRepositoryService.getInstance().findById(idBook);
        if(!book.isPresent()){
            throw new NotFoundException("Not found book " + idBook);
        }
        Optional<UserModel> user = userRepositoryService.getInstance().findById(idUser);
        if(!user.isPresent()){
            throw new NotFoundException("Not found User " + idUser);
        }
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

    public void delete(Long id) throws NotFoundException{
        Optional<UserModel> opUser = userRepositoryService.getInstance().findByEvaluateId(id);
        Optional<BookModel> opBook = bookRepositoryService.getInstance().findByEvaluateId(id);
        Optional<EvaluateModel> opEvaluate = evaluateRepositoryService.getInstance().findById(id);
        if(!opEvaluate.isPresent()){
            throw new NotFoundException("Not found Evaluate " + id);
        }
        if(!opUser.isPresent()){
            throw new NotFoundException("Not found User " + id);
        }
        if(!opBook.isPresent()){
            throw new NotFoundException("Not found Book " + id);
        }
        opBook.get().removeEvaluateFromEvaluateList(opEvaluate.get());
        opUser.get().removeEvaluateFromEvaluateList(opEvaluate.get());
        bookRepositoryService.getInstance().save(opBook.get());
        userRepositoryService.getInstance().save(opUser.get());
        evaluateRepositoryService.getInstance().deleteById(id);
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
