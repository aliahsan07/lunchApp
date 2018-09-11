package com.venturedive.rotikhilao.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="customer")
@Data
public class Customer extends User {

    @Column(name="balance")
    private Integer balance;

}
