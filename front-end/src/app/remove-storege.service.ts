import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { CanshowService } from './canshow.service';
import { LocalstoregeService } from './localstorege.service';

@Injectable({
  providedIn: 'root'
})
export class RemoveStoregeService implements CanActivate{

  constructor(public router: Router,private localstoregee: LocalstoregeService,private canshowService: CanshowService ) { }

  canActivate(): boolean {
    let isTokenPresent = this.localstoregee.getValue('canshow');
    if (isTokenPresent == 'true') {
      this.localstoregee.setValue('canshow','false');
      this.canshowService.setcanShow(false); 
      this.router.navigate(['/']);
      return false;
    }else{
      return true;
    }
    
  } 
}

