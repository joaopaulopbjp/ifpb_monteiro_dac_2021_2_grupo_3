package com.bookstore.backend.application.service.address;

import com.bookstore.backend.domain.model.address.AddressModel;
//import com.bookstore.backend.domain.model.user.AdminModel;
import com.bookstore.backend.domain.model.user.PersonModel;
//import com.bookstore.backend.domain.model.user.UserModel;
import com.bookstore.backend.infrastructure.exception.NotFoundException;
import com.bookstore.backend.infrastructure.persistence.repository.address.AddressRepository;
import com.bookstore.backend.infrastructure.persistence.repository.person.PersonRepository;
import com.bookstore.backend.infrastructure.persistence.service.address.AddressRepositoryService;

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
	private AddressRepository addressRepository;

	@Autowired
	private AddressRepositoryService addressRepositoryService; 

	@Autowired
	private PersonRepository userRepositoryService;

	public AddressModel save(AddressModel address, String username) throws NotFoundException {

		Optional<PersonModel> user = userRepositoryService.findByUsername(username);

		verifyAddress(address);

		address = addressRepository.save(address);
		user.get().addAddressToAddressList(address);
		userRepositoryService.save(user.get());
		return address;


	}

	private void verifyAddress (AddressModel address) {
		address.setZipCode(address.getZipCode().replaceAll("-", ""));
		if(address.getZipCode().length() != 8) {
			throw new IllegalArgumentException("ZipCode must be 8");
		}
	}

	public void delete(Long id, String username) throws Exception {
		PersonModel person = userRepositoryService.findByUsername(username).get();
		AddressModel address = addressRepository.findById(id).get();

		person.removeAddressFromAddressList(address);
		userRepositoryService.save(person);
		
		addressRepository.deleteById(id);

	}

	public AddressModel update(AddressModel addressModel, String username) throws NotFoundException{

		return addressRepositoryService.update(addressModel);
	}

	public AddressModel findById(Long id, String username) throws Exception {
		Optional<AddressModel> addressOp = addressRepository.findById(id);
		if(!addressOp.isPresent()){
			throw new NotFoundException("Not found address with id " + id);
		}

		return addressOp.get();
	}

	public List<AddressModel> findAll(String username) throws NotFoundException {

		Optional<PersonModel> userOp = userRepositoryService.findByUsername(username);
		List<AddressModel> addressList = userOp.get().getAddressList();

		if(addressList.isEmpty())
			throw new NotFoundException("You don't have any address");
		return addressList;

	}

	public List<AddressModel> findAll() throws NotFoundException {

		return addressRepository.findAll();
	}
}
