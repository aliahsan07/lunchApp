package com.venturedive.rotikhilao.model;

import com.venturedive.rotikhilao.enums.UserType;
import com.venturedive.rotikhilao.model.audit.DateAudit;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user")
@Getter
@Setter
@EqualsAndHashCode()
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User extends DateAudit{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_name")
    private String userName;

    @Column(name="password")
    private String password;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    //private Role role;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    private UserType role;

    public User(String userName, String password, String firstName, String lastName) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(){

    }
}
