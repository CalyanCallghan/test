import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AlertServiceService } from '../alert-service.service';
import { EmployeeService } from '../employee.service';
import { Login } from '../login';


@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent {
  login: Login = new Login();
  form: FormGroup = this.formBuilder.group({
    code: ['', [Validators.required,Validators.email,emailDomainValidator]],
    password: ['', [Validators.required,Validators.pattern('(?=\\D*\\d)(?=[^a-z]*[a-z])(?=[^A-Z]*[A-Z]).{8,30}')]],
    cpassword: ['', [Validators.required]]
  }, { validator: passwordMatchValidator });

  get cpassword() { return this.form.get('cpassword'); }
  get password() { return this.form.get('password'); }

  onPasswordInput() {
    if (this.form.hasError('passwordMismatch'))
      this.cpassword.setErrors({ 'passwordMismatch': true });
  }

  public hasError = (controlName: string, errorName: string) => {
    return this.form.controls[controlName].hasError(errorName);
  }
  constructor(private router: Router, private employeeService: EmployeeService,
    private formBuilder: FormBuilder, private alertService: AlertServiceService) { }

  onSubmit() {
    if (this.form.invalid) {
      return false;
    }
    this.employeeService
      .resetPassword(this.form.get('password').value, this.form.get('code').value).subscribe(data => {
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

export const passwordMatchValidator: ValidatorFn = (formGroup: FormGroup): ValidationErrors | null => {
  if (formGroup.get('password').value === formGroup.get('cpassword').value)
    return { passwordMismatch: false };
  else
    return { passwordMismatch: true };
};

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

