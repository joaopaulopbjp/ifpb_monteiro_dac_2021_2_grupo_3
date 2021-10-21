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
        book.get().addEvaluateToEvaluateList(evaluate);
        bookRepositoryService.getInstance().save(book.get());
        user.get().addEvaluateToEvaluateList(evaluate);
        userRepositoryService.getInstance().save(user.get());
        return evaluateRepositoryService.getInstance().save(evaluate);
    }

    public void delete(Long id) throws NotFoundException{
        Optional<EvaluateModel> evaluate = evaluateRepositoryService.getInstance().findById(id);
        if(!evaluate.isPresent()){
            throw new NotFoundException("Not found Evaluate " + id);
        }
        
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
