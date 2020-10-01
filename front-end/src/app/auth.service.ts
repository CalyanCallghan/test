import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { LocalstoregeService } from './localstorege.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService implements CanActivate {
  constructor(public router: Router,private localstoregee: LocalstoregeService ) {}

  canActivate(): boolean {
    let isTokenPresent = this.localstoregee.getValue('canshow');
    if (isTokenPresent != 'true') {
      this.router.navigate(['/']);
      return false;
    }
    return true;
  } 
}
