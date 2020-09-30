import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthService } from './auth.service';
import { CreateAdminComponent } from './create-admin/create-admin.component';
import { CreateEmployeeComponent } from './create-employee/create-employee.component';
import { EmployeeDetailsComponent } from './employee-details/employee-details.component';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import { LoginComponent } from './login/login.component';
import { UpdateEmployeeComponent } from './update-employee/update-employee.component';

const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'employees', component: EmployeeListComponent,canActivate: [AuthService]  },
  { path: 'add', component: CreateEmployeeComponent, canActivate: [AuthService] },
  { path: 'update/:id', component: UpdateEmployeeComponent,canActivate: [AuthService]  },
  { path: 'details/:id', component: EmployeeDetailsComponent,canActivate: [AuthService]  },
  { path: 'create', component: CreateAdminComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
