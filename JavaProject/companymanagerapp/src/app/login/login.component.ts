import { Component, OnInit } from '@angular/core';

import { AuthService } from '../auth/auth.service';
import { TokenStorageService } from '../auth/token-storage.service';
import { AuthLoginInfo } from '../auth/login-info';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  counter: number;
  form: any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  roleAdmin = false;
  roleUser = false;
  errorMessage = '';
  roles: string[] = [];
  private loginInfo: AuthLoginInfo;
 
  constructor(private authService: AuthService, private tokenStorage: TokenStorageService, private router: Router) { }
 
  info: any;

  ngOnInit() {

   this.info = {
      token: this.tokenStorage.getToken(),
      emaill: this.tokenStorage.getEmail(),
      authorities: this.tokenStorage.getAuthorities()
    };

    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getAuthorities();
    }

    for(this.counter = 0; this.counter < this.roles.length; this.counter++){
        if(this.roles[this.counter] == "ROLE_ADMIN")
            this.roleAdmin = true;
        if(this.roles[this.counter] == "ROLE_USER")
            this.roleUser = true;
    }
  }

  functionRedirectAdmin(){
    this.router.navigate(['admin']);
  }

  functionRedirectUser(){
    this.router.navigate(['user']);
  }


  
  logout() {
    this.tokenStorage.signOut();
    window.location.reload();
  }

 
  onSubmit() {
 
    this.loginInfo = new AuthLoginInfo(
      this.form.email,
      this.form.password);
 
    this.authService.attemptAuth(this.loginInfo).subscribe(
      data => {
        this.tokenStorage.saveToken(data.token);
        this.tokenStorage.saveEmail(data.email);
        this.tokenStorage.saveAuthorities(data.roles);
 
        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.tokenStorage.getAuthorities();
        this.reloadPage();
      },
      error => {
        console.log(error);
        this.errorMessage = error.error.message;
        this.isLoginFailed = true;
      }
    );
  }

  reloadPage() {
    window.location.reload();
  }


}
