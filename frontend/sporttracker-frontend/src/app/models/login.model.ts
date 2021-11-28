export class LoginModel{
  private username:string;
  private password:string;

  constructor(username:string,password:string){
    this.password=password;
    this.username=username;
  }
}
