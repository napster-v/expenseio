package com.expense.io.project.auth;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String authority;

    @ManyToMany(mappedBy = "roles")
    private Set<AppUser> users = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Permission> permissions = new HashSet<>();

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Set<AppUser> getUsers() {
        return users;
    }

    public void setUsers(Set<AppUser> users) {
        this.users = users;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }
}
