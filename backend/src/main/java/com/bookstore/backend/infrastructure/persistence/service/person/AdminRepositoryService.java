package com.bookstore.backend.infrastructure.persistence.service.person;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.NoSuchElementException;

import com.bookstore.backend.domain.model.user.AdminModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.repository.person.AdminRepository;
import com.bookstore.backend.infrastructure.utils.Utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AdminRepositoryService {
    @Autowired
    private AdminRepository adminRepository;

    private Utils utils = new Utils();

    @Value("${numberOfItemsPerPage}")
    private String numberOfItemsPerPage;

    public AdminRepository getInstance() {
        return adminRepository;
    }

    public AdminModel update(AdminModel adminModel, String username) throws NotFoundException {
        AdminModel adminDB = null;
        try {
            adminDB = adminRepository.findByUsername(username).get();

        } catch (NoSuchElementException e) {
            throw new NotFoundException();
        }

        if(adminModel.getPassword() != null) {
            try {
                adminModel.setPassword(utils.shar256(adminModel.getPassword()));
            } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        BeanUtils.copyProperties(adminModel, adminDB, Utils.getNullPropertyNames(adminModel));

        return adminRepository.save(adminDB);
    }

    public List<AdminModel> findAll(int pageNumber) throws NotFoundException {
        Pageable pageable = PageRequest.of(pageNumber, Integer.parseInt(numberOfItemsPerPage), Sort.unsorted());
        Page<AdminModel> pages = adminRepository.findAll(pageable);

        if(pages.isEmpty()) throw new NotFoundException();
        
        return pages.getContent();
    }
}
