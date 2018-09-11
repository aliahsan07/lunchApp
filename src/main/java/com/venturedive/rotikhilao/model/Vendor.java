package com.venturedive.rotikhilao.model;

import javax.persistence.*;

@Entity
@Table(name = "vendor")
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="vendor_name")
    private String name;

}
