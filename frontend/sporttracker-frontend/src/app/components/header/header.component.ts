import { UserDetailsModel } from './../../models/user-models/user-details.model';
import { StorageService } from './../../services/storage/storage.service';
import { AuthService } from './../../services/auth/auth.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  isNavbarOpen=false;

  constructor(private authService:AuthService, private storageService:StorageService) { }

  ngOnInit(): void {
  }

  onLogout(){
    this.authService.logout();
  }
  toggleNavbar() {
    this.isNavbarOpen = !this.isNavbarOpen;
  }

  showRoutesForLoggedIn(){
    return this.authService.isLoggedIn();
  }
  showRoutesForNewUsers(){
    return !this.authService.isLoggedIn();
  }
  showUsername(){
    try{
      return <string>(JSON.parse(this.storageService.getValue("user_details")).username);
    }catch(ex){
      return '';
    }

  }


}
