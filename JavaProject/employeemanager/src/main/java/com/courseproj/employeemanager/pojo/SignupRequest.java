package com.courseproj.employeemanager.pojo;

import java.util.Set;

public class SignupRequest {

//
//    private String firstname;
//    private String lastname;
    private String email;
    private Set<String> roles;
    private String password;

//    public String getFirstName() {
//        return firstname;
//    }
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

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
