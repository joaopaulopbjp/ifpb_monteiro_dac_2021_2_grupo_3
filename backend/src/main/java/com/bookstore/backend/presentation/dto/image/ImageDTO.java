package com.bookstore.backend.presentation.dto.image;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImageDTO {
    private Long id;

    private String base64;

    private Long idBook;
}
