import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  private baseUrl = 'http://localhost:8086/springboot-crud-rest/api/v1/employees';

  constructor(private http: HttpClient) { }

  getEmployee(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  createEmployee(employee: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, employee);
  }

  updateEmployee(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  deleteEmployee(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  getEmployeesList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

  isPresent(email: String, userId: String): Observable<any> {
    return this.http.get(`${this.baseUrl}/${email}/${userId}`);
  }
  createAdmin(admin: Object): Observable<Object> {
    this.baseUrl = 'http://localhost:8086/springboot-crud-rest/api/v1/admin';
    return this.http.post(`${this.baseUrl}`, admin);
  }
  resetPassword(password: String, code: String): Observable<any> {
    this.baseUrl = 'http://localhost:8086/springboot-crud-rest/api/v1/resetPassword';
    return this.http.get(`${this.baseUrl}/${password}/${code}`);
  }
}