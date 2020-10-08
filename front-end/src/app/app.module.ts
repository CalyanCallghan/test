import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CreateEmployeeComponent } from './create-employee/create-employee.component';
import { EmployeeDetailsComponent } from './employee-details/employee-details.component';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import { FormsModule } from '@angular/forms';
import { UpdateEmployeeComponent } from './update-employee/update-employee.component';
import { ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './login/login.component';
import { AlertComponentComponent } from './alert-component/alert-component.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CreateAdminComponent } from './create-admin/create-admin.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { MaterialModule } from './material.module';
import { PasswordStrengthMeterModule } from 'angular-password-strength-meter';
import { MatPasswordStrengthModule } from '@angular-material-extensions/password-strength'

@NgModule({
  declarations: [
    AppComponent,
    CreateEmployeeComponent,
    EmployeeDetailsComponent,
    EmployeeListComponent,
    UpdateEmployeeComponent,
    LoginComponent,
    AlertComponentComponent,
    CreateAdminComponent,
    ResetPasswordComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    BrowserModule,
    BrowserAnimationsModule,
    MaterialModule,
    PasswordStrengthMeterModule,
    MatPasswordStrengthModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

