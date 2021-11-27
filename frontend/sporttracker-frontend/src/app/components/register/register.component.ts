import { NewUser } from './../../models/new-user.model';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { PasswordsMustMatch } from '../../utils/validators/passwords-must-match';


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
  constructor(private fb: FormBuilder, private router: Router) {}

  //ngOnInit
  ngOnInit(): void {
  }
  //onSubmit
  onSubmit(){
    if (this.registerForm.valid && this.registerForm.touched){
      let user = new NewUser(
        this.registerForm.value.username,
        this.registerForm.value.email,
        this.registerForm.value.password
      )
      console.log("New user data:",user);
    } else {
      console.log(this.registerForm);
      window.alert("Warning! Form is not correctly inputed.")
    }

  }
}
