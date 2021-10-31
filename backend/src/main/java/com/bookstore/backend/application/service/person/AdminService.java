package com.bookstore.backend.application.service.person;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bookstore.backend.domain.model.sale.ShoppingCartModel;
import com.bookstore.backend.domain.model.user.AdminModel;
import com.bookstore.backend.domain.model.user.PersonModel;
import com.bookstore.backend.domain.model.user.UserModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.service.person.AdminRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.person.UserRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.sale.ShoppingCartRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepositoryService adminRepositoryService;

    @Autowired
    private UserRepositoryService userRepositoryService;

    @Autowired
    private UserService userService;
    
    @EventListener(ApplicationReadyEvent.class)
    private void init() {
        List<AdminModel> adminlist = adminRepositoryService.getInstance().findAll();
        if(adminlist.isEmpty()) {
            AdminModel admin = new AdminModel(0l, "admin", "admin@email.com", "12345", null, null, null);
            adminRepositoryService.getInstance().save(admin);
        }
    }

    public AdminModel save(Long userId) throws IllegalArgumentException, NotFoundException {
        Optional<UserModel> userOp = userRepositoryService.getInstance().findById(userId);
        if(!userOp.isPresent())
            throw new NotFoundException("User not found with id " + userId);

        AdminModel admin = new AdminModel(userOp.get().getId(),
            userOp.get().getUsername(),
            userOp.get().getEmail(),
            userOp.get().getPassword(),
            userOp.get().getAddressList(),
            null,
            userOp.get().getSaleHistory());
        
        userService.delete(userId);
        AdminModel adminSaved = adminRepositoryService.getInstance().save(admin);
        
        return adminSaved;
    }
    
    public AdminModel update(AdminModel adminModel) throws NotFoundException {
        // if(!userRepositoryService.getInstance().existsById(user.getId()))
        //     throw new NotFoundException("User with id " + user.getId() + " not found");

        // if(user.getEmail() != null && !validate(user.getEmail()))
        //     throw new IllegalArgumentException(user.getEmail() + " is a invalid Email");
        
        // if(user.getUsername() != null && (user.getUsername().length() < 3 || user.getUsername().length() > 15)) {
        //     throw new IllegalArgumentException("Username must be between 3 and 15 characters");
        // }

        // if(user.getPassword() != null && user.getPassword().length() < 5) {
        //     throw new IllegalArgumentException("Password must be at least 5 characters");
        // }    

        // user = userRepositoryService.update(user);
        // return user;
        return null;
    }

    public void delete(Long adminId) throws NotFoundException {
        // boolean flag = userRepositoryService.getInstance().existsById(personId);
        // if(!flag)
        //     throw new NotFoundException("User with id " + personId + " not found");

        // userRepositoryService.getInstance().deleteById(personId);
    }

    public List<AdminModel> findAll(int pageNumber) throws NotFoundException {
        List<AdminModel> adminList = adminRepositoryService.findAll(pageNumber);
        return adminList;
    }

    public AdminModel findById(Long id) throws NotFoundException {
        // Optional<UserModel> user = userRepositoryService.getInstance().findById(id);
        // if(!user.isPresent())
        //     throw new NotFoundException("User with id " + id + " not found");
        
        // return user.get();
        return null;
    }
    
    public AdminModel findByEmail(AdminModel email) throws NotFoundException {
        // Optional<UserModel> user = userRepositoryService.getInstance().findByEmail(email);
        // if(!user.isPresent())
        //     throw new NotFoundException("User with email " + email + " not found");
        
        // return user.get();
        return null;
    }

    public AdminModel findByUsername(String username) throws NotFoundException {
        Optional<AdminModel> user = adminRepositoryService.getInstance().findByUsername(username);
        if(!user.isPresent())
            throw new NotFoundException("User with username " + username + " not found");
        
        return user.get();
    }

    // private static boolean validate(String emailStr) {
    //         Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
    //         return matcher.find();
    // }
}
