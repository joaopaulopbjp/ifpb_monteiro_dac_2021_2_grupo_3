package com.bookstore.backend.infrastructure.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class ModelMapperService {
    
    private static ModelMapper modelMapper;

    public static Object convertToDTO(Object object, Class<?> dtoTarget) {
        modelMapper = new ModelMapper();
        return modelMapper.map(object, dtoTarget);
    }

    public static Object convertToModel(Object dto, Class<?> model) {
        modelMapper = new ModelMapper();
        return modelMapper.map(dto, model);
    }
}
