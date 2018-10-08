package com.venturedive.rotikhilao.model;

import com.venturedive.rotikhilao.enums.UserType;
import com.venturedive.rotikhilao.model.audit.DateAudit;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user")
@Getter
@Setter
@EqualsAndHashCode()
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User extends DateAudit {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_name")
    private String userName;

    @Column(name="name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    private UserType role;

    @Column(name="image_url")
    private String imageUrl;

    public User(String userName, String imageUrl, String firstName) {
        this.userName = userName;
        this.imageUrl = imageUrl;
        this.name = firstName;
    }

    public User(){

    }
}
