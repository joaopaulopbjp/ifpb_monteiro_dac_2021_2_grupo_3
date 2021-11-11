package com.bookstore.backend.presentation.dto.evaluate;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EvaluateDTO {
    private Long id;

    private Integer starNumber;
    
    private String comment;

    private Long idBook;
    
    private Long idUser;
}
