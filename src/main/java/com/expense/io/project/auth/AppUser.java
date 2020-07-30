package com.expense.io.project.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"username", "email"}))
public class AppUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @NotBlank(message = "First name cannot be blank")
    private String firstName;

    @Column(nullable = false)
    @NotBlank(message = "Last name cannot be blank")
    private String lastName;

    @Email(message = "The email provided is not a valid email.")
    @Column(nullable = false)
    @NotBlank(message = "Email is required for password reset and cannot be blank.")
    private String email;

    @NotBlank(message = "Username cannot be blank.")
    @Column(nullable = false)
    private String username;

    @NotBlank(message = "Password cannot be blank.")
    @Column(nullable = false)
    private String password;
    private boolean enabled = true;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();
//    @OneToMany(mappedBy = "user")
//    private List<Category> category;


    public AppUser() {
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

//    public List<Category> getCategory() {
//        return category;
//    }
//
//    public void setCategory(List<Category> category) {
//        this.category = category;
//    }
}
