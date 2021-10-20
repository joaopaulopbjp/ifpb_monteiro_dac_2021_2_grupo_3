package com.bookstore.backend.application.service.person;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bookstore.backend.domain.model.sale.ShoppingCartModel;
import com.bookstore.backend.domain.model.user.UserModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.service.person.UserRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.sale.ShoppingCartRepositoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
        Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Autowired
    private UserRepositoryService userRepositoryService;

    @Autowired
    private ShoppingCartRepositoryService shoppingCartRepositoryService;

    public UserModel save(UserModel user) throws IllegalArgumentException {

        if(user.getUsername().equals(null))
            throw new IllegalArgumentException();

        if(user.getEmail().equals(null))
            throw new IllegalArgumentException();

        if(user.getPassword().equals(null))
            throw new IllegalArgumentException();

        if(validate(user.getEmail()))
            throw new IllegalArgumentException(user.getEmail() + " is a invalid Email");
        
        if(user.getUsername().length() < 3 || user.getUsername().length() > 15) {
            throw new IllegalArgumentException("Username must be between 3 and 15 characters");
        }

        if(user.getPassword().length() < 5) {
            throw new IllegalArgumentException("Password must be at least 5 characters");
        }

        ShoppingCartModel shoppingCart = new ShoppingCartModel();
        shoppingCart = shoppingCartRepositoryService.getInstance().save(shoppingCart);
        user.setShoppingCart(shoppingCart);

        user = userRepositoryService.getInstance().save(user);
        try {

        } catch (DataIntegrityViolationException e) {
            shoppingCartRepositoryService.getInstance().delete(shoppingCart);
            throw e;
        }
        return user;
    }
    
    public List<UserModel> findAll(int pageNumber) throws NotFoundException {
        List<UserModel> userList = userRepositoryService.findAll(pageNumber);
        return userList;
    }

    public UserModel findById(Long id) throws NotFoundException {
        Optional<UserModel> user = userRepositoryService.getInstance().findById(id);
        if(!user.isPresent())
            throw new NotFoundException("User with id " + id + " not found");
        
        return user.get();
    }
    
    public UserModel findByEmail(String email) throws NotFoundException {
        Optional<UserModel> user = userRepositoryService.getInstance().findByEmail(email);
        if(!user.isPresent())
            throw new NotFoundException("User with email " + email + " not found");
        
        return user.get();
    }

    public UserModel findByUsername(String username) throws NotFoundException {
        Optional<UserModel> user = userRepositoryService.getInstance().findByUsername(username);
        if(!user.isPresent())
            throw new NotFoundException("User with username " + username + " not found");
        
        return user.get();
    }


    private static boolean validate(String emailStr) {
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
            return matcher.find();
    }
}
