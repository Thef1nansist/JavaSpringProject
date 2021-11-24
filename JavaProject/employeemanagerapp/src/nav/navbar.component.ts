import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Employee } from 'src/app/employee';
import { EmployeeService } from 'src/app/employee.service';
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';
import { RouterOutlet } from '@angular/router';

@Component({
 selector: 'navbar',
 templateUrl: './navbar.component.html',
 styleUrls: ['./navbar.component.css'],
  //template:``

})
export class NavComponent implements OnInit {

  
  public employees: Employee[];
  public editEmployee: Employee;
  public deleteEmployee: Employee;
  filterText ='';


  constructor(private employeeService: EmployeeService){}


  ngOnInit() {
    
  }


  }
  