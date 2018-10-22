package com.venturedive.rotikhilao.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponse implements Serializable {


    private static final long serialVersionUID = -1007099974760213488L;

    private String isAuthorized;

    private String jwtToken;

    private String name;
}
