package com.courseproj.employeemanager.appUser.authorization.controller;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import com.courseproj.employeemanager.appUser.authorization.email.EmailSender;
import com.courseproj.employeemanager.appUser.authorization.token.ConfirmationToken;
import com.courseproj.employeemanager.appUser.authorization.token.ConfirmationTokenService;
import com.courseproj.employeemanager.appUser.model.user.AppUser;
import com.courseproj.employeemanager.appUser.model.role.ERole;
import com.courseproj.employeemanager.appUser.model.role.Role;
import com.courseproj.employeemanager.appUser.registration.RegistrationService;
import com.courseproj.employeemanager.appUser.service.UserDetailsServiceImpl;
import com.courseproj.employeemanager.jwt.JwtUtils;
import com.courseproj.employeemanager.pojo.JwtResponse;
import com.courseproj.employeemanager.pojo.LoginRequest;
import com.courseproj.employeemanager.pojo.MessageResponse;
import com.courseproj.employeemanager.pojo.SignupRequest;
import com.courseproj.employeemanager.appUser.repository.RoleRepository;
import com.courseproj.employeemanager.appUser.repository.UserRepository;
import com.courseproj.employeemanager.appUser.service.UserDetailsImpl;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserRepository userRespository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RegistrationService registrationService;

    @Autowired
    UserDetailsServiceImpl appUserService;

    @Autowired
    EmailSender emailSender;


    @PostMapping("/signin")
    public ResponseEntity<?> authUser(@RequestBody LoginRequest loginRequest) {


        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

//        userDetails.getFirstname(),
//        userDetails.getLastname(),
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles));
    }




    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest) {


        if (userRespository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is exist"));
        }

            // signupRequest.getFirstName(),signupRequest.getLastName(),
        AppUser user = new AppUser(signupRequest.getEmail(),
                passwordEncoder.encode(signupRequest.getPassword()));

        //???????????????????????????????????????????????????


        String token = appUserService.signUpUser(user);

        String link = "http://localhost:8081/api/auth/confirm?token=" + token;
        emailSender.send(
                signupRequest.getEmail(),
                registrationService.buildEmail(signupRequest.getEmail(), link));

       // ???????????????????????????????????????????????????


        Set<String> reqRoles = signupRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (reqRoles == null) {
            Role userRole = roleRepository
                    .findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error, Role USER is not found"));
            roles.add(userRole);
        } else {
            reqRoles.forEach(r -> {
                switch (r) {
                    case "admin":
                        Role adminRole = roleRepository
                                .findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error, Role ADMIN is not found"));
                        roles.add(adminRole);

                        break;

                    default:
                        Role userRole = roleRepository
                                .findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error, Role USER is not found"));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);

        userRespository.save(user);

        return ResponseEntity.ok(new MessageResponse("User CREATED"));
    }


    @GetMapping(path = "/confirm")
    public String confirm(@RequestParam("token") String token) {

        return registrationService.confirmToken(token);
    }


}