package com.venturedive.rotikhilao.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
@Getter
@Setter
@PrimaryKeyJoinColumn(name = "user_id")
public class Admin extends User {

    public Admin(String userName, String password, String firstName, String lastName){
        super(userName, password, firstName, lastName);

    }


}
