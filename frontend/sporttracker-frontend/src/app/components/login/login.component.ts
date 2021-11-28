import { LoginModel } from './../../models/login.model';
import { AuthService } from './../../services/auth/auth.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private fb: FormBuilder, private router: Router, private authService:AuthService) { }

  ngOnInit(): void {
    if (this.authService.isLoggedIn()){
      this.router.navigate(['/']);
    }
  }

  loginForm = this.fb.group({
    username: ["", Validators.required],
    password: ["", Validators.required]
  });

  onSubmit(){
    if (this.loginForm.touched && this.loginForm.valid){
      const loginModel:LoginModel = new LoginModel(
        this.loginForm.value.username,
        this.loginForm.value.password
      );
      this.authService.login(loginModel);
    }else{
      window.alert("Username and Password are required!")
    }
  }

}
