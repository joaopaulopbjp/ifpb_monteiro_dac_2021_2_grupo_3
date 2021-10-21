package com.bookstore.backend.application.controller.evaluate;

import java.util.List;

import com.bookstore.backend.application.service.evaluate.EvaluateService;
import com.bookstore.backend.domain.model.evaluation.EvaluateModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.modelmapper.ModelMapperService;
import com.bookstore.backend.presentation.dto.evaluate.EvaluateDTO;
import com.bookstore.backend.presentation.response.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/evaluate")
@CrossOrigin
public class EvaluateController {
    
    @Autowired
    private EvaluateService evaluateService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody EvaluateDTO dto){
        EvaluateModel evaluate = (EvaluateModel) ModelMapperService.convertToModel(dto, EvaluateModel.class);
        try {
            evaluate = evaluateService.save(evaluate, dto.getIdBook(), dto.getIdUser());
            dto = (EvaluateDTO) ModelMapperService.convertToDTO(evaluate, EvaluateDTO.class);
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(e.getMessage()));
        } 
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody EvaluateDTO dto){
        try {
            evaluateService.delete(dto.getId());
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody EvaluateDTO dto){
        try {
            EvaluateModel evaluate = (EvaluateModel) ModelMapperService.convertToModel(dto, EvaluateModel.class);
            evaluate = evaluateService.update(evaluate);
            dto = (EvaluateDTO)ModelMapperService.convertToDTO(evaluate, EvaluateDTO.class);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(e.getMessage()));
        }
    }

    @GetMapping("/find-all")
    public ResponseEntity<?> findAll(){
        try {
            List<EvaluateModel> evaluateList = evaluateService.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(evaluateList);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(e.getMessage()));
        }
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<?> findById(@RequestBody EvaluateDTO dto){
        try {
            EvaluateModel evaluate = evaluateService.findById(dto.getId());
            return ResponseEntity.status(HttpStatus.OK).body(evaluate);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(e.getMessage()));
        }
    }
}
