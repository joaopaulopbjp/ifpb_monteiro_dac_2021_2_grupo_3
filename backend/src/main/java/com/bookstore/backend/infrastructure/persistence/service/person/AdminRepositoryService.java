package com.bookstore.backend.infrastructure.persistence.service.person;

import java.util.NoSuchElementException;

import com.bookstore.backend.domain.model.user.AdminModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.repository.person.AdminRepository;
import com.bookstore.backend.infrastructure.utils.Utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminRepositoryService {
    @Autowired
    private AdminRepository adminRepository;

    public AdminRepository getInstace() {
        return adminRepository;
    }

    public AdminModel update(AdminModel adminModel) throws NotFoundException {
        AdminModel adminDB = null;
        try {
            adminDB = adminRepository.findById(adminModel.getId()).get();

        } catch (NoSuchElementException e) {
            throw new NotFoundException();
        }

        BeanUtils.copyProperties(adminModel, adminDB, Utils.getNullPropertyNames(adminModel));

        return adminRepository.save(adminDB);
    }
}
