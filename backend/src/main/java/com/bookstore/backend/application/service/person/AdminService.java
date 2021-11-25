package com.bookstore.backend.application.service.person;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bookstore.backend.domain.model.user.AdminModel;
import com.bookstore.backend.domain.model.user.UserModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.service.person.AdminRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.person.UserRepositoryService;
import com.bookstore.backend.infrastructure.utils.AdminVerify;
import com.bookstore.backend.infrastructure.utils.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepositoryService adminRepositoryService;

    @Autowired
    private UserRepositoryService userRepositoryService;

    @Autowired
    private AdminVerify adminVerify;

    private Utils utils = new Utils();

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
        Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    
    @EventListener(ApplicationReadyEvent.class)
    private void init() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        List<AdminModel> adminlist = adminRepositoryService.getInstance().findAll();
        if(adminlist.isEmpty()) {
            AdminModel admin = new AdminModel(0l, "admin", "admin@email.com", "admin", null, null, null);
            admin.setPassword(utils.shar256(admin.getPassword()));
            adminRepositoryService.getInstance().save(admin);
        }
    }

    public AdminModel save(Long userId, String username) throws NotFoundException, Exception {
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
        
        delete(userId, username);
        AdminModel adminSaved = adminRepositoryService.getInstance().save(admin);
        
        return adminSaved;
    }
    
    public AdminModel update(AdminModel admin, String username) throws NotFoundException {
        if(admin.getEmail() != null && !validate(admin.getEmail()))
            throw new IllegalArgumentException(admin.getEmail() + " is a invalid Email");
        
        if(admin.getUsername() != null && (admin.getUsername().length() < 3 || admin.getUsername().length() > 15)) {
            throw new IllegalArgumentException("Username must be between 3 and 15 characters");
        }

        if(admin.getPassword() != null && admin.getPassword().length() < 5) {
            throw new IllegalArgumentException("Password must be at least 5 characters");
        }    

        admin = adminRepositoryService.update(admin, username);
        return admin;
    }

    public void delete(String username) throws NotFoundException {
        Optional<AdminModel> admin = adminRepositoryService.getInstance().findByUsername(username);

        adminRepositoryService.getInstance().deleteById(admin.get().getId());
    }

    public void delete(Long personId, String username) throws NotFoundException, Exception {
        boolean flagAdmin = adminRepositoryService.getInstance().existsById(personId);
        boolean flagUser = userRepositoryService.getInstance().existsById(personId);
        if(!adminVerify.isAdmin(username))
            throw new Exception("you can't delete, you don't have permission to delete.");
        if(!flagUser && !flagAdmin){
            throw new NotFoundException("User with id " + personId + " not found");
        }
        if(flagUser){
            userRepositoryService.getInstance().deleteById(personId);
        }
        adminRepositoryService.getInstance().deleteById(personId);
    }

    public List<AdminModel> findAll(int pageNumber) throws NotFoundException {
        List<AdminModel> adminList = adminRepositoryService.findAll(pageNumber);
        return adminList;
    }

    public AdminModel findById(Long id) throws NotFoundException {
        Optional<AdminModel> admin = adminRepositoryService.getInstance().findById(id);
        if(!admin.isPresent())
            throw new NotFoundException("Admin with id " + id + " not found.");
        
        return admin.get();
    }
    
    public AdminModel findByEmail(String email) throws NotFoundException {
        Optional<AdminModel> admin = adminRepositoryService.getInstance().findByEmail(email);
        if(!admin.isPresent())
            throw new NotFoundException("Admin with email " + email + " not found.");
        
        return admin.get();
    }

    public AdminModel findByUsername(String username) throws NotFoundException {
        Optional<AdminModel> user = adminRepositoryService.getInstance().findByUsername(username);
        if(!user.isPresent())
            throw new NotFoundException("Admin with username " + username + " not found");
        
        return user.get();
    }

    private static boolean validate(String emailStr) {
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
            return matcher.find();
    }
}
