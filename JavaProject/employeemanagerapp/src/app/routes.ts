import { Router, RouterModule, Routes } from "@angular/router";
import { UserComponent } from "./user/user.component";
import { NavComponent } from "src/nav/navbar.component";
import { AppComponent } from "./app.component";
import { AdminComponent } from "./admin/admin.component";
import { LoginComponent } from "./login/login.component";
import { RegisterComponent } from "./register/register.component";
import { NgModule } from "@angular/core";

export const appRoutes: Routes = [
    { 
        path: 'admin', 
        component: AdminComponent 
    },
    { 
        path: 'user',
        component: UserComponent 
    },
    { 
        path: 'signin',
        component: LoginComponent 
    },   
    { 
        path: 'signup',
        component: RegisterComponent 
    },
    { 
        path: '',
        redirectTo: 'signin',
        pathMatch: 'full'
    }
];

