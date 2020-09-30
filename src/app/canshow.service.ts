import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CanshowService {
  private canShow = new Subject<any>();
  constructor() { }
  setcanShow(canShow: any) {
    this.canShow.next(canShow);
  }

  getcanShow(): Observable<any> {
    return this.canShow.asObservable();
  }

  clearcanShows() {
    this.canShow.next(null);
  }
}
