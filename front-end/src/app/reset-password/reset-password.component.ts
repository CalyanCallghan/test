import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AlertServiceService } from '../alert-service.service';
import { EmployeeService } from '../employee.service';
import { Login } from '../login';
import { MustMatch } from '../MustMatch';


@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent {
  loginForm: FormGroup;
  submitted = false;
  login: Login = new Login();
  form: FormGroup = new FormGroup({
    code: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
    cpassword: new FormControl('', [Validators.required])
  });

  public hasError = (controlName: string, errorName: string) => {
    return this.form.controls[controlName].hasError(errorName);
  }
  constructor(private router: Router, private employeeService: EmployeeService,
    private formBuilder: FormBuilder, private alertService: AlertServiceService) { }


  get f() { return this.form.controls; }

  onSubmit() {
    this.submitted = true;
    if (this.form.invalid) {
      return false;
    }
    this.employeeService
      .resetPassword(this.f.password.value, this.f.code.value).subscribe(data => {
        this.login = data;
        if (this.login.status == "Y") {
          this.router.navigate(['/']);
        } else {
          console.log(this.login.message)
          this.alertService.error(this.login.message);
        }
      },
        error => console.log(error));
  }

}

