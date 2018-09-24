package com.venturedive.rotikhilao.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table
@PrimaryKeyJoinColumn(name = "user_id")
@NoArgsConstructor
@Getter
@Setter
public class OfficeBoy extends User {

    private static final long serialVersionUID = 1L;

    @Column(name = "created_by")
    private Long createdBy;

    public OfficeBoy(String userName, String password, String firstName, String lastName) {
        super(userName, password, firstName, lastName);
    }

}
