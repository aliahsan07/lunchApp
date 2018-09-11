package com.venturedive.rotikhilao.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
@Getter
@Setter
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "user_id")
public class Admin extends User {


}
