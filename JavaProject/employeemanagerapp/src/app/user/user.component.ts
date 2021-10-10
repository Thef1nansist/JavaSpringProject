import { Component, OnInit } from '@angular/core';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { NONE_TYPE } from '@angular/compiler';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css'],  
  
  

})
export class UserComponent implements OnInit {
  

  public employees: Employee[];
  public editEmployee: Employee;
  public deleteEmployee: Employee;
  filterText ='';


  constructor(private employeeService: EmployeeService, private router: Router){}

  functionRedirectSignIn(){
    this.router.navigate(['/signin']);
  }

  ngOnInit() {
    this.getEmployees();
  }

  public getEmployees(): void {
    this.employeeService.getEmployees().subscribe(
      (response: Employee[]) => {
        this.employees = response;
        console.log(this.employees);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public searchEmployees(key: string): void {
    console.log(key);
    const results: Employee[] = [];
    for (const employee of this.employees) {
      if (employee.name.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || employee.email.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || employee.phone.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || employee.jobTitle.toLowerCase().indexOf(key.toLowerCase()) !== -1) {
        results.push(employee);
      }
    }
    this.employees = results;
    if (results.length === 0 || !key) {
      this.getEmployees();
    }
  }


  public filterEmployeesByName(){
    this.employees.sort((a,b) => a.name > b.name ? 1 : -1);
  }

  public filterEmployeesById(){
    this.employees.sort((a,b) => a.id > b.id ? 1 : -1);  
  }


}


