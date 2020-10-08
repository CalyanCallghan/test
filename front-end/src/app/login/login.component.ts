import { Login } from './../login';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EmployeeService } from '../employee.service';
import { AlertServiceService } from '../alert-service.service';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Employee } from '../employee';
import { CanshowService } from '../canshow.service';
import { LocalstoregeService } from '../localstorege.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{
  hide = true;
  login: Login = new Login();
  loading = false;
  loginForm: FormGroup;
  submitted = false;
  
  constructor(private route: ActivatedRoute, private router: Router,
    private employeeService: EmployeeService, private alertService: AlertServiceService,
    private formBuilder: FormBuilder, private canshowService: CanshowService,
    private localstoregee: LocalstoregeService) { 
    }
    ngOnInit(): void {
      
    }
  form: FormGroup = new FormGroup({
    password: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required, Validators.email,emailDomainValidator])
  });
  public hasError = (controlName: string, errorName: string) => {
    return this.form.controls[controlName].hasError(errorName);
  }

  get f() { return this.form.controls; }

  onSubmit() {
    this.submitted = true;

    if (this.form.invalid) {
      return;
    }
    this.employeeService.isPresent(this.f.email.value, this.f.password.value)
      .subscribe(data => {
        this.login = data;
        if (this.login.status == "Y") {
          this.router.navigate(['employees']);
          this.localstoregee.setValue('canshow', 'true');
          this.canshowService.setcanShow('true');
        } else {
          console.log(this.login.message)
          this.alertService.error(this.login.message);
          this.loading = false;
        }
      }, error => console.log(error));
  }

}

function emailDomainValidator(control: FormControl) {
  let email = control.value;
  if (email && email.indexOf("@") != -1) {
    let [_, domain] = email.split("@");
    if (domain !== "onpassive.com") {
      return {
        emailDomain: {
          parsedDomain: domain
        }
      }
    }
  }
  return null;
}
