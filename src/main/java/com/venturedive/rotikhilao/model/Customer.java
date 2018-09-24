package com.venturedive.rotikhilao.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="customer")
@PrimaryKeyJoinColumn(name="user_id")
@NoArgsConstructor
@Getter
@Setter
public class Customer extends User {

    @Column(name="balance")
    private Integer balance;

    public Customer(String userName, String password, String firstName, String lastName){
        super(userName, password, firstName, lastName);

    }
}
