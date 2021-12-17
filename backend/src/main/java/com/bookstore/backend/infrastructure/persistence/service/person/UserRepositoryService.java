package com.bookstore.backend.infrastructure.persistence.service.person;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
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

    private Utils utils = new Utils();

    public UserRepository getInstance() {
        return userRepository;
    }

    public UserModel update(UserModel userModel, String username) throws NotFoundException {
        UserModel userDB = null;
        try {
            userDB = userRepository.findByUsername(username).get();

        } catch (NoSuchElementException e) {
            throw new NotFoundException();
        }

        try {
            if(userModel.getPassword() != null && utils.shar256(userModel.getPassword()) != userDB.getPassword()) {
                userModel.setPassword(utils.shar256(userModel.getPassword()));
            }
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
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
