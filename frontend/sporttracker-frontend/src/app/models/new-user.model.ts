export class NewUserModel{
  private username:string;
  private password:string;
  private email:string;

  constructor(username:string, email:string, password:string){
    this.email=email;
    this.password=password;
    this.username=username;
  }
}
