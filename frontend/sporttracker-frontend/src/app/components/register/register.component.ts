import { AuthService } from './../../services/auth/auth.service';
import { NewUserModel } from './../../models/user-models/new-user.model';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { PasswordsMustMatch } from '../../utils/validators/passwords-must-match';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

   //Variables
   registerForm = this.fb.group({
     username: ["", Validators.required],
     email: ["", [Validators.required, Validators.email]],
     password: ["", Validators.required],
     passwordConfirmation: ["", Validators.required]
   }, {validator: PasswordsMustMatch('password', 'passwordConfirmation')});






  //Constructor
  constructor(private fb: FormBuilder, private router: Router, private authService:AuthService) {}

  //ngOnInit
  ngOnInit(): void {
    this.authService.logout();
  }
  //onSubmit
  onSubmit(){
    if (this.registerForm.valid && this.registerForm.touched){
      let user = new NewUserModel(
        this.registerForm.value.username,
        this.registerForm.value.email,
        this.registerForm.value.password
      )
      this.authService.createNewUser(user).subscribe(
        () => {this.router.navigate(['/login'])},
        err => {
          console.log("Error while registering:",<HttpErrorResponse>err.status);
          window.alert("Warning! Username or Email already taken!")}
      );
    }
    else {
      window.alert("Warning! Form is not correctly inputed.")
    }

  }
}
