import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private fb: FormBuilder, private router: Router) { }

  ngOnInit(): void {
  }

  loginForm = this.fb.group({
    username: ["", Validators.required],
    password: ["", Validators.required]
  });

  onSubmit(){
    console.log("On submit from login component");
  }

}
