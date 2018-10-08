package com.venturedive.rotikhilao.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "google_credentials")
@NoArgsConstructor
@AllArgsConstructor
public class GoogleCredentials {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    private String sub;

    @Column
    private String email;

    @Column(nullable = false, columnDefinition = "BIT(1)", name="email_verified")
    private Boolean emailVerified;

    @Column
    private Integer exp;
}
