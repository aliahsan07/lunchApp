package com.venturedive.rotikhilao.model;

import com.venturedive.rotikhilao.enums.UserType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "user_roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private UserType name;
}
