package com.courseproj.employeemanager.repository;

import java.util.Optional;

import com.courseproj.employeemanager.appuser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long>{
    Optional<AppUser> findByEmail(String email);
    Boolean existsByEmail(String email);

}