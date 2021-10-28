package com.bookstore.backend.application.services.address;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.bookstore.backend.application.service.address.AddressService;
import com.bookstore.backend.application.service.person.UserService;
import com.bookstore.backend.domain.model.address.AddressModel;
import com.bookstore.backend.domain.model.user.UserModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.service.person.UserRepositoryService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class AddressTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepositoryService userRepositoryService;

    @Autowired
    private AddressService addressService;
    
    @Test
    @Order(1)
    public void addressSaveSucess() throws NotFoundException {
        UserModel userModel = new UserModel(0l, "Thauan", "thauan@email.com", "12345", null, null,
            null, null, null);

            userModel = userService.save(userModel);

        AddressModel addressModel = new AddressModel(0l, "Rua nova", "123", "16473-009", "Monteiro", "Paraíba");

        assertNotNull(addressService.save(addressModel, userModel.getId()));
    }

    @Test
    @Order(2)
    public void addressSaveError() throws NotFoundException {
        UserModel userModel = new UserModel(0l, "user", "user@email.com", "12345", null, null,
            null, null, null);

        userModel = userService.save(userModel);

        Long userId = userModel.getId();

        AddressModel addressModel = new AddressModel(0l, "Rua nova", "123", "16473-0091", "Monteiro", "Paraíba");

        assertThrows(IllegalArgumentException.class, () -> addressService.save(addressModel, userId));
    }

    @Test
    @Order(3)
    public void addressRemoveSucess() throws NotFoundException {
        UserModel userModel = userRepositoryService.getInstance().findAll().stream().findFirst().get();
        assertFalse(userModel.getAddressList().isEmpty());
        
        addressService.delete(1l);

        userModel = userRepositoryService.getInstance().findAll().stream().findFirst().get();

        assertTrue(userModel.getAddressList().isEmpty());
    }

    @Test
    @Order(4)
    public void addressRemoveError() throws NotFoundException {
        assertThrows(NotFoundException.class, () -> addressService.delete(10000l));
    }
}
