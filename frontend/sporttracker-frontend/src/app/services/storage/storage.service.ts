import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  constructor() { }


  storeValue(key:string, value: string | null){
    if (value){
      localStorage.setItem(key,value);
    } else {
      console.log("Nothing to store, value is empty!");
    }
  }

  clearStorate(){
    localStorage.clear();
  }

  getValue(key:string){
    let tmp :string|null = localStorage.getItem(key);
    return tmp? tmp : '';
  }
  printStorage(){
    console.log("Printing localStorage:",localStorage);
  }
}
