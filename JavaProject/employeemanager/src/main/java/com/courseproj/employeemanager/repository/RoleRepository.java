package com.courseproj.employeemanager.repository;

import java.util.Optional;

import com.courseproj.employeemanager.appuser.ERole;
import com.courseproj.employeemanager.appuser.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
    Optional<Role> findByName(ERole name);
}
