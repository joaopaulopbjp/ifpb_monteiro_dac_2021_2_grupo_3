package com.bookstore.backend.infrastructure.modelmapper;

import org.modelmapper.ModelMapper;

public class ModelMapperService {
    private static ModelMapper modelMapper;

    public static Object convertToDTO(Object object, Object dtoTarget) {
        return modelMapper.map(object, dtoTarget.getClass());
    }

    public static Object convertToModel(Object dto, Object model) {
        return modelMapper.map(dto, model.getClass());
    }
}
