import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LocalstoregeService {

  constructor() { }
  setValue(key: string, value: string) {
    localStorage.setItem(key, value)
  }
  getValue(value: string):string {
    return localStorage.getItem(value);
  }
}
