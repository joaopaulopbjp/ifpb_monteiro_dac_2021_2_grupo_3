package com.bookstore.backend.domain.model.user;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "T_ADMIN")
public class AdminModel extends PersonModel{
    
}
