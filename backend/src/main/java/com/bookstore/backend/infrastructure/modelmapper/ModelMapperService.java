package com.bookstore.backend.infrastructure.modelmapper;

import org.modelmapper.ModelMapper;

public class ModelMapperService {
    private static ModelMapper modelMapper;

    public static Object convertToDTO(Object object, Class<?> dtoTarget) {
        return modelMapper.map(object, dtoTarget);
    }

    public static Object convertToModel(Object dto, Class<?> model) {
        return modelMapper.map(dto, model);
    }
}
