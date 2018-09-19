package com.venturedive.rotikhilao.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.venturedive.rotikhilao.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserPrincipal implements UserDetails {

    private Long id;

    private String firstName;

    private String lastName;

    private String username;

    @JsonIgnore
    private String password;

    //private Collection<? extends GrantedAuthority> authorities;

    private GrantedAuthority authority;

//    public UserPrincipal(Long id, String name, String username, String email, String password, Collection<? extends GrantedAuthority> authorities) {
//        this.id = id;
//        this.name = name;
//        this.username = username;
//        this.email = email;
//        this.password = password;
//        this.authorities = authorities;
//    }


    public UserPrincipal(Long id, String username, String password, GrantedAuthority authority,
                         String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.authority = authority;
    }

    public static UserPrincipal create(User user) {

        GrantedAuthority authority =  new SimpleGrantedAuthority(user.getRole().getName().name());
        return new UserPrincipal(
                user.getId(),
                user.getUserName(),
                user.getPassword(),
                authority,
                user.getFirstName(),
                user.getLastName()
        );
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public GrantedAuthority getAuthority() {
        return authority;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
//    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
