package com.courseproj.employeemanager.appUser.authorization.email;

public interface EmailSender {
    void send(String to, String email);
}
