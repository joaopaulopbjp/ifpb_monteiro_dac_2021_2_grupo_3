package com.bookstore.backend.domain.model.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.bookstore.backend.domain.model.address.AddressModel;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class PersonModel implements UserDetails{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "USERNAME", nullable = false, unique = true)
    private String username;

    @Column(name = "IMAGE", columnDefinition="TEXT")
    private String image;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;
    
    @ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    private List<Perfil> perfils;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(
        name = "T_PERSON_ADDRESS_JOIN", 
        joinColumns = @JoinColumn(name = "PERSON_ID", nullable = false), 
        inverseJoinColumns = @JoinColumn(name = "ADDRESS_ID", nullable = false))
    private List<AddressModel> addressList;

  
    public PersonModel() {
		super();
	}

	public PersonModel(Long id, String username, String image, String email, String password, List<Perfil> perfils,
			List<AddressModel> addressList) {
		super();
		this.id = id;
		this.username = username;
		this.image = image;
		this.email = email;
		this.password = password;
		this.perfils = perfils;
		this.addressList = addressList;
	}

	public boolean addAddressToAddressList(AddressModel addressModel) {
        if(addressList != null) {
            addressList.add(addressModel);
        } else {
            addressList = new ArrayList<>();
            addAddressToAddressList(addressModel);
        }
        return true;
    }

    public boolean removeAddressFromAddressList(AddressModel addressModel) {
        if(addressList != null) {
            return addressList.remove(addressModel);
        } 
        return false;
    }

    @Override
    public String toString() {
        return String.format("PERSON [ID: %s - USERNAME: %s - EMAIL: %s]", getId(), getUsername(), getEmail()); 
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return perfils;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Perfil> getPerfils() {
		return perfils;
	}

	public void setPerfils(List<Perfil> perfils) {
		this.perfils = perfils;
	}

	public List<AddressModel> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<AddressModel> addressList) {
		this.addressList = addressList;
	}
}
