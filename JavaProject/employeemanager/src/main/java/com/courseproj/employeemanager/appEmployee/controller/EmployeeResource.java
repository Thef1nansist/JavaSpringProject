package com.courseproj.employeemanager.appEmployee.controller;

import com.courseproj.employeemanager.exception.EmployeeException;
import com.courseproj.employeemanager.appEmployee.model.Employee;
import com.courseproj.employeemanager.appEmployee.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@Log
@RestController
//@CrossOrigin(origins = "http://localhost:4200/admin")
@RequestMapping("/employee")
public class EmployeeResource {
    private final EmployeeService employeeService;

    public EmployeeResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees(boolean exception)
                    throws EmployeeException{
        if (exception) {
            throw new EmployeeException("EmployeeException in getAllEmployees Controller");
        }
        List<Employee> employees = employeeService.findAllEmployees();
        log.info("Fetching all employees...");
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @Operation(summary = "Get an employee by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the employee",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Employee.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Employee not found",
                    content = @Content) })
    @GetMapping("/find/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id, boolean exception)
                    throws EmployeeException {
            if (exception) {
                throw new EmployeeException("EmployeeException in getEmployeeById Controller");
            }
        Employee employee = employeeService.findEmployeeById(id);
        log.info("Getting an employee by ID...");
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping("/add")                                                                       // BindingResult bindingResult
    public ResponseEntity<Employee> addEmployee(@RequestBody @Valid Employee employee, boolean exception)
                    throws EmployeeException{
        if (exception) {
            throw new EmployeeException("EmployeeException in addEmployee Controller");
        }

        Employee newEmployee = employeeService.addEmployee(employee);
        log.info("Adding employee...");
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody @Valid Employee employee, boolean exception)
                    throws EmployeeException{
        if (exception) {
            throw new EmployeeException("EmployeeException in updateEmployee Controller");
        }
        Employee updateEmployee = employeeService.updateEmployee(employee);
        log.info("Updating employee...");
        return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable("id") Long id, boolean exception)
                    throws EmployeeException{
        if (exception) {
            throw new EmployeeException("EmployeeException in deleteEmployee Controller");
        }
        employeeService.deleteEmployee(id);
        log.info("Deleting employee...");
        return new ResponseEntity<>(HttpStatus.OK);
    }



}



