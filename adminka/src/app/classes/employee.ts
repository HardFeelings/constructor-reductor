import { HttpClient } from "@angular/common/http";
import { ResponseInfo } from "../models/responesInfo";
import { Observable } from 'rxjs';

export class Employee{
  idEmployee: number
  login: string
  password: string
  admin: boolean | null;

  delete(http: HttpClient) : Observable<boolean> {
    return http.delete<boolean>(`/api/v1/security/admin/employee/${this.idEmployee}`)
}
  ser(): any {
    return {
      idEmployee: this.idEmployee,
      login: this.login,
      password: this.password,
      admin: this.admin,
    }
  }

  save(http: HttpClient) : Observable<ResponseInfo<Employee>> {
    return http.post<ResponseInfo<Employee>>('/api/v1/security/admin/employee', this.ser())
  }
}
