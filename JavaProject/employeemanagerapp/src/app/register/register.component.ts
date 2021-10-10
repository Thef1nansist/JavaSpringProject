import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AuthService } from '../auth/auth.service';
import { SignUpInfo } from '../auth/signup-info';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  form: any = {};
  signupInfo: SignUpInfo;
  isSignedUp = false;
  isSignUpFailed = false;
  errorMessage = '';

  isEnabled = true;
  isntEnabled = false;

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit() { }

  functionRedirectSignUp(){
    this.router.navigate(['/signup']);
  }
  functionRedirectSignIn(){
    this.router.navigate(['/signin']);
  }


  onSubmit() {
    console.log(this.form);

    this.signupInfo = new SignUpInfo(
      this.form.email,
      this.form.password
      );

    this.authService.signUp(this.signupInfo).subscribe(
      data => {
        console.log(data);
        this.isSignedUp = true;
        this.isSignUpFailed = false;
      },
      error => {
        console.log(error);
        this.errorMessage = error.error.message;
        this.isSignUpFailed = true;
      }
    );

  }
}