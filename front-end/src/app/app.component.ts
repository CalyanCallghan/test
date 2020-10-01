
import { Router, RouterModule } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { takeUntil } from 'rxjs/internal/operators/takeUntil';
import { CanshowService } from './canshow.service';
import { Subject } from 'rxjs';
import { LocalstoregeService } from './localstorege.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = '';
  public readonly destroy$: Subject<void> = new Subject<void>();
  isAuthToRead:boolean = false;
  ngOnInit(): void {
    this.canshowService.getcanShow()
    .pipe(takeUntil(this.destroy$))
    .subscribe((isTrue) => {
      this.isAuthToRead = (isTrue == 'true');
      console.log("changed===>"+isTrue);
    });
    let canShow = this.localstoregee.getValue('canshow');
    this.canshowService.setcanShow(canShow); 
  }

  constructor(private canshowService: CanshowService, private router:Router,private localstoregee:LocalstoregeService) {
  }
  logout():void{
    this.localstoregee.setValue('canshow','false');
    this.canshowService.setcanShow(false); 
    this.router.navigate(['/']);
  }
}
