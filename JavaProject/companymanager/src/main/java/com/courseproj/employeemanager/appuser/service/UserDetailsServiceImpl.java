package com.courseproj.employeemanager.appUser.service;

import com.courseproj.employeemanager.appUser.authorization.token.ConfirmationToken;
import com.courseproj.employeemanager.appUser.authorization.token.ConfirmationTokenService;
import com.courseproj.employeemanager.appUser.model.user.AppUser;
import com.courseproj.employeemanager.appUser.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final ConfirmationTokenService confirmationTokenService;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));
        return UserDetailsImpl.build(user);
    }

//???????????????????????????????????????????????????

    public String signUpUser(AppUser appUser) {

        boolean userExists = userRepository
                .findByEmail(appUser.getEmail())
                .isPresent();

        if (userExists) {
            throw new IllegalStateException("email already taken");
        }

        userRepository.save(appUser);

    String token = UUID.randomUUID().toString();

    ConfirmationToken confirmationToken = new ConfirmationToken(
            token,
            LocalDateTime.now(),
            LocalDateTime.now().plusMinutes(15),
            appUser
    );

        confirmationTokenService.saveConfirmationToken(
            confirmationToken);

    return token;

    }

//???????????????????????????????????????????????????

    public int enableAppUser(String email) {
        return userRepository.enableAppUser(email);
    }

}