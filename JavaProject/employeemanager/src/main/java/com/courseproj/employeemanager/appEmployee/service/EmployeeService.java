package com.courseproj.employeemanager.appEmployee.service;

import com.courseproj.employeemanager.exception.EmployeeException;
import com.courseproj.employeemanager.appEmployee.model.Employee;
import com.courseproj.employeemanager.appEmployee.repository.EmployeeRepo;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Log
@Transactional
public class EmployeeService {
    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public Employee addEmployee(Employee employee){
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeeRepo.save(employee);
    }

    public Employee updateEmployee(Employee employee){
        return  employeeRepo.save(employee);
    }

    public void deleteEmployee(Long id){
        employeeRepo.deleteEmployeeById(id);
    }

    public Employee findEmployeeById(Long id){
        return employeeRepo.findEmployeeById(id)
                .orElseThrow(() -> {
                    new EmployeeException("User by id " + id + " was not found");
                    log.info("User by id" + id + "wasn't found");
                    return null;
                });
    }

    public List<Employee> findAllEmployees(){
        return employeeRepo.findAll();
    }





}
