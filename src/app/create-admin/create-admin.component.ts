import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Admin } from '../admin';
import { EmployeeService } from '../employee.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AlertServiceService } from '../alert-service.service';
import { MustMatch } from '../MustMatch';

@Component({
  selector: 'app-create-admin',
  templateUrl: './create-admin.component.html',
  styleUrls: ['./create-admin.component.css']
})
export class CreateAdminComponent implements OnInit {
  submitted = false;
  admin: Admin = new Admin();
  registerForm: FormGroup;
  constructor(private router: Router, private employeeService: EmployeeService,
    private formBuilder: FormBuilder, private alertService: AlertServiceService) { }

  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      emailId: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      cpassword: ['', Validators.required]
    },
    {
      validator: MustMatch('password', 'cpassword')
  });
  }

  get f() { return this.registerForm.controls; }

  onSubmit() {
    this.submitted = true;

    if (this.registerForm.invalid) {
      return false;
    }

    this.saveDetails();

  }
  saveDetails() {
    this.employeeService
      .createAdmin(this.admin).subscribe(data => {
        console.log(data);
        this.router.navigate(['/']);
      },
        error => console.log(error));
  }
}

