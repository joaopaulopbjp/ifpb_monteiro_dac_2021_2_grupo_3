package com.bookstore.backend.infrastructure.persistence.service.evaluate;

import java.util.NoSuchElementException;

import com.bookstore.backend.domain.model.evaluation.EvaluateModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.repository.evaluate.EvaluateRepository;
import com.bookstore.backend.infrastructure.utils.Utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvaluateRepositoryService {
    
    @Autowired
    private EvaluateRepository evaluateRepository;

    public EvaluateRepository getInstance() {
        return evaluateRepository;
    }

    public EvaluateModel update(EvaluateModel evaluateModel) throws NotFoundException {
        EvaluateModel evaluateDB = null;
        try {
            evaluateDB = evaluateRepository.findById(evaluateModel.getId()).get();

        } catch (NoSuchElementException e) {
            throw new NotFoundException();
        }

        BeanUtils.copyProperties(evaluateModel, evaluateDB, Utils.getNullPropertyNames(evaluateModel));

        return evaluateRepository.save(evaluateDB);
    } 
}
