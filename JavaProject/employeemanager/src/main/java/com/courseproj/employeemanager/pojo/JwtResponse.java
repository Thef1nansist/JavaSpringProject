package com.courseproj.employeemanager.pojo;


import java.util.List;

public class JwtResponse {

    private Long id;
    private String token;
    private String type = "Bearer";

//    private String firstname;
//    private String lastname;
    private String email;
    private List<String> roles;

    //String firstname, String lastname,
    public JwtResponse(String token, Long id, String email, List<String> roles) {
        this.token = token;
        this.id = id;
//        this.firstname = firstname;
//        this.lastname = lastname;
        this.email = email;
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//
//    public String getFirstname() {
//        return firstname;
//    }
//
//    public void setFirstname(String firstname) {
//        this.firstname = firstname;
//    }
//
//    public String getLastname() {
//        return lastname;
//    }
//
//    public void setLastname(String lastname) {
//        this.lastname = lastname;
//    }


    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

}