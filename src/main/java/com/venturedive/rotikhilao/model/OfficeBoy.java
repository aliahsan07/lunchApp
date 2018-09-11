package com.venturedive.rotikhilao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "user_id")
public class OfficeBoy extends User {

    private static final long serialVersionUID = 1L;

    @Column(name = "created_by")
    private Long createdBy;
}
