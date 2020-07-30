package com.expense.io.project.auth;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Permission implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String authority;

    @ManyToMany(mappedBy = "permissions")
    private Set<Role> roles = new HashSet<>();

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
