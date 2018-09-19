package com.venturedive.rotikhilao.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="customer")
@Data
@PrimaryKeyJoinColumn(name="user_id")
public class Customer extends User {

    @Column(name="balance")
    private Integer balance;

    public Customer(String userName, String password, String firstName, String lastName){
        super(userName, password, firstName, lastName);

    }
}
