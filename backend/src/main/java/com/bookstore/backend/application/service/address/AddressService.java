package com.bookstore.backend.application.service.address;

import com.bookstore.backend.domain.model.address.AddressModel;
import com.bookstore.backend.domain.model.user.AdminModel;
import com.bookstore.backend.domain.model.user.UserModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.service.address.AddressRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.person.AdminRepositoryService;
import com.bookstore.backend.infrastructure.persistence.service.person.UserRepositoryService;
import com.bookstore.backend.infrastructure.utils.AdminVerify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AdminVerify adminVerify;
    
    @Autowired
    private AddressRepositoryService addressRepositoryService;

    @Autowired
    private UserRepositoryService userRepositoryService;

    @Autowired
    private AdminRepositoryService adminRepositoryService;

    public AddressModel save(AddressModel address, String username) throws NotFoundException {
        if(!adminVerify.isAdmin(username)) {
            Optional<UserModel> user = userRepositoryService.getInstance().findByUsername(username);
    
            verifyAddress(address);
            
            address = addressRepositoryService.getInstance().save(address);
            user.get().addAddressToAddressList(address);
            userRepositoryService.getInstance().save(user.get());
            return address;

        } else {
            Optional<AdminModel> admin = adminRepositoryService.getInstance().findByUsername(username);

            verifyAddress(address);

            address = addressRepositoryService.getInstance().save(address);
            admin.get().addAddressToAddressList(address);
            adminRepositoryService.getInstance().save(admin.get());
            return address;
        }
    }

    private void verifyAddress (AddressModel address) {
        address.setZipCode(address.getZipCode().replaceAll("-", ""));
        if(address.getZipCode().length() != 8) {
            throw new IllegalArgumentException("ZipCode must be 8");
        }
    }

    public void delete(Long id, String username) throws Exception {
        Optional<UserModel> userOp = userRepositoryService.getInstance().findByAddressId(id);
        Optional<AdminModel> adminOp = adminRepositoryService.getInstance().findByAddressId(id);
        if(!userOp.isPresent() && !adminOp.isPresent())
            throw new NotFoundException("Can't found address with id " + id);

        if(!adminVerify.isAdmin(username)) {
            userOp = userRepositoryService.getInstance().findByUsername(username);
            boolean flag = false;
            flag = userOp.get().getAddressList().stream().filter(address -> address.getId() == id).findFirst().isPresent();

            if(!flag)
                throw new Exception("You can't delete this address because it belongs to another user");

        }

        Optional<AddressModel> address = addressRepositoryService.getInstance().findById(id);
        if(userOp.isPresent()) {
            userOp.get().removeAddressFromAddressList(address.get());
            userRepositoryService.getInstance().save(userOp.get());

        } else {
            adminOp.get().removeAddressFromAddressList(address.get());
            adminRepositoryService.getInstance().save(adminOp.get());
        }
        addressRepositoryService.getInstance().deleteById(address.get().getId());

    }

    public AddressModel update(AddressModel addressModel) throws NotFoundException{
        return addressRepositoryService.update(addressModel);
    }

    public AddressModel findById(Long id, String username) throws Exception {
        Optional<AddressModel> addressOp = addressRepositoryService.getInstance().findById(id);
        if(!addressOp.isPresent()){
            throw new NotFoundException("Not found address with id " + id);
        }

        if(!adminVerify.isAdmin(username)) {
            Optional<UserModel> userOp = userRepositoryService.getInstance().findByUsername(username);
            boolean flag = false;
            flag = userOp.get().getAddressList().stream().filter(address -> address.getId() == id).findFirst().isPresent();

            if(!flag)
                throw new Exception("You can't get this address because it belongs to another user");

        }
        return addressOp.get();
    }

    public List<AddressModel> findAll(String username) throws NotFoundException {
        if(!adminVerify.isAdmin(username)) {
            Optional<UserModel> userOp = userRepositoryService.getInstance().findByUsername(username);
            List<AddressModel> addressList = userOp.get().getAddressList();

            if(addressList.isEmpty())
                throw new NotFoundException("You don't have any address");
            return addressList;

        }
        return addressRepositoryService.getInstance().findAll();
    }
}
