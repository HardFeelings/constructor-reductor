import { Injectable } from '@angular/core';
import { ABaseServiceService } from './abase-service.service';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Employee } from '../models/employee';
import { Page } from '../models/page';
import { ResponseInfo } from '../models/responesInfo';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService extends ABaseServiceService{
  private  employeeUrl = 'security/admin/employee';

  constructor(http: HttpClient) {
    super(http, 'api/v1');
  }

  getPageEmployees(offset: number): Observable<ResponseInfo<Page<Employee>>> {
    let params = new HttpParams()
        .set('offset', offset.toString())
    return this.get<ResponseInfo<Page<Employee>>>(this.employeeUrl,  params);
  }

  deleteEmployee(id:number): Observable<ResponseInfo<Boolean>> {
    return this.delete<ResponseInfo<Boolean>>(`${this.employeeUrl}/${id}`);
  }

  saveEmployee(employee: Employee): Observable<ResponseInfo<Employee>>  {
    return this.post<ResponseInfo<Employee>> (`${this.employeeUrl}`, employee);
  }
}
