package com.courseproj.employeemanager.appUser.model.user;

import com.courseproj.employeemanager.appUser.model.role.Role;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


/*
@UniqueConstraint(columnNames = "firstname"),
@UniqueConstraint(columnNames = "lastname"),
*/

@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email"),
                @UniqueConstraint(columnNames = "password")
        })

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class AppUser{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    private String firstname;
//    private String lastname;
    private String email;
    private String password;
    private Boolean enabled = false;
    private Boolean locked = false;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    // String firstname,
    // String lastname,
    public AppUser(String email,
                   String password) {
//        this.firstname = firstname;
//        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public String getFirstName() {
//        return firstname;
//    }
//
//    public void setFirstName(String firstname) {
//        this.firstname = firstname;
//    }
//
//    public String getLastName() {
//        return lastname;
//    }
//
//    public void setLastName(String lastname) {
//        this.lastname = lastname;
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return !locked;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return enabled;
    }
}