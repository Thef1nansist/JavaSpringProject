package com.courseproj.employeemanager.appEmployee.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

@Entity
public class Employee extends com.courseproj.employeemanager.appEmployee.Employee implements Serializable {

    //@Size(min = 0, max = 100)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false, unique = true)
    private Long id;

    @Size(min = 1, max = 30)
    @Pattern(regexp = "\\D+",message = "Field NAME must contain only letters") // [^0-9]*
    private String name;

    @Email(message = "Email should be valid")
    private String email;

   @Size(min = 1, max = 30)
   @Pattern(regexp = "\\D+",message = "Field JOB TITLE must contain only letters")
    private String jobTitle;

    @Size(min = 12, max = 13)
    @Pattern(regexp = "\\d+",message = "Field PHONE must contain only digits")
    private String phone;


    private String imageUrl;



    @Column(nullable = false, updatable = false)
    private String employeeCode;

    // For Tests

    private static long countId = 0;

    private static Map<Long, Employee> allEmployees = new HashMap<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return name == employee.name &&
                Objects.equals(name, employee.name) &&
                email == employee.email;
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, email);
    }


    private boolean hasEmployee(){
        for (Employee employee : allEmployees.values()){
            if (employee.equals(this) && employee.hashCode() == this.hashCode()){
                return true;
            }
        }
        return false;
    }


    // список всех пользователей
    public static List<com.courseproj.employeemanager.appEmployee.Employee> getAllEmployees(){
        return new ArrayList<>(allEmployees.values());
    }

    // список пользователей по рабочей должности
    public static List<Employee> getAllEmployees(String jobTitle){
        List<Employee> listAllEmployees = new ArrayList<>();
        for (Employee employee : allEmployees.values()){
            if (employee.jobTitle == jobTitle){
                listAllEmployees.add(employee);
            }
        }
        return listAllEmployees;
    }

    // количество работников в общем списке
    public static int getHowManyEmployees(){
        return allEmployees.size();
    }

    // количество работников по признаку рабочей должности
    public static int getHowManyEmployees(String jobTitle){
        return getAllEmployees(jobTitle).size();
    }


    public Employee(String name, String email, String jobTitle) {

        if (name != null && !name.isEmpty() &&
            email != null && !email.isEmpty() &&
            jobTitle != null && !jobTitle.isEmpty())
        {

            this.name = name;
            this.email = email;
            this.jobTitle = jobTitle;


            if (!hasEmployee()) {
                countId++;
                this.id = countId;
                allEmployees.put(id, this);
            }

        }

    }


    //



    public Employee() {};

    public Employee(String name, String email, String jobTitle, String phone, String imageUrl, String employeeCode) {

        // For Tests
        if (allEmployees == null){
            allEmployees = new HashMap<Long, Employee>();
        }
        //

        this.name = name;
        this.email = email;
        this.jobTitle = jobTitle;
        this.phone = phone;
        this.imageUrl = imageUrl;
        this.employeeCode = employeeCode;

        // For Tests
        if (!hasEmployee()){
            countId++;
            this.id = countId;
            allEmployees.put(id, this);
        }
        //
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", phone='" + phone + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
