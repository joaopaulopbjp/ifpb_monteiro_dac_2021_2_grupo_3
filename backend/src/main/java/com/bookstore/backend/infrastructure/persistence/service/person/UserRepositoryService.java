package com.bookstore.backend.infrastructure.persistence.service.person;

import java.util.List;
import java.util.NoSuchElementException;

import com.bookstore.backend.domain.model.user.UserModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.repository.person.UserRepository;
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
public class UserRepositoryService {
    @Autowired
    private UserRepository userRepository;

    @Value("${numberOfItemsPerPage}")
    private String numberOfItemsPerPage;

    public UserRepository getInstance() {
        return userRepository;
    }

    public UserModel update(UserModel userModel) throws NotFoundException {
        UserModel userDB = null;
        try {
            userDB = userRepository.findById(userModel.getId()).get();

        } catch (NoSuchElementException e) {
            throw new NotFoundException();
        }

        BeanUtils.copyProperties(userModel, userDB, Utils.getNullPropertyNames(userModel));

        return userRepository.save(userDB);
    }

    public List<UserModel> findAll(int pageNumber) throws NotFoundException {
        Pageable pageable = PageRequest.of(pageNumber, Integer.parseInt(numberOfItemsPerPage), Sort.unsorted());
        Page<UserModel> pages = userRepository.findAll(pageable);

        if(pages.isEmpty()) throw new NotFoundException();
        
        return pages.getContent();
    }
}
