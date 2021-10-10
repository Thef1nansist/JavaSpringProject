import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Employee } from 'src/app/employee';
import { EmployeeService } from 'src/app/employee.service';
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';
import { RouterOutlet } from '@angular/router';

@Component({
 selector: 'footer-bar',
 templateUrl: './footer.component.html',
 styleUrls: ['./footer.component.css'],
  //template:``

})
export class FooterComponent implements OnInit {

    ngOnInit(): void {
        
    }


  
  }
  