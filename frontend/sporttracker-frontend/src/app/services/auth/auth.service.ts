import { Router } from '@angular/router';
import { StorageService } from './../storage/storage.service';
import { LoginModel } from './../../models/login.model';
import { NewUserModel } from './../../models/new-user.model';
import { HttpClient, HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly backendUrl = "http://localhost:8000";
  constructor(private http:HttpClient, private storageService:StorageService, private router:Router) { }

  private composeHeader(){
    return {
      'Content-Type':  'application/json',
      'Authorization': 'Bearer '+ this.storageService.getValue('jwt')
    }
  }


  createNewUser(newUser:NewUserModel){
    return this.http.post(`${this.backendUrl}/users`,newUser,{observe:'response'});
  }

  login(loginModel:LoginModel){
    this.http.post(`${this.backendUrl}/auth/login`,loginModel,{observe:'response'}).subscribe(
      res=>{
        console.log("Response from auth/login endpoint:",res);
        if (res.status==200){
          this.storageService.storeValue("jwt",res.headers.get('pragma'));
          this.storeUserDetails();
          this.router.navigate(['/']);
        }},
        err=>{window.alert("Username or password are invalid!")});
  }
  storeUserDetails(){
    if (this.storageService.getValue('jwt')!=''){
      this.http.get(`${this.backendUrl}/user_details`,{headers:this.composeHeader()}).subscribe(
        res=>{this.storageService.storeValue('user_details',JSON.stringify(res))}
      );
    }else{
      console.log("Trying to get user details without JWT!");
    }

  }
  logout(){
    this.storageService.clearStorate();
  }

  isLoggedIn(){
    return this.storageService.getValue('jwt')!='';
  }
}
