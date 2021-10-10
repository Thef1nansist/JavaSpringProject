import { THIS_EXPR } from "@angular/compiler/src/output/output_ast";


export class SignUpInfo{
    email: string;
    role: string[];
    password: string;
    enabled: boolean;

    constructor(email: string, password: string){         
        this.email = email;
        this.password = password;
        this.role = ['user'];
    }
}