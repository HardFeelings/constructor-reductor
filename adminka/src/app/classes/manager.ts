import { HttpClient } from "@angular/common/http";
import { Observable } from 'rxjs';
import { ResponseInfo } from "../models/responesInfo";

export class Manager {
  idManager: number;
  shortName: string;
  fullName: string;
  position: string;
  email: string;
  phoneNumber: string;
  idUser: number;

  delete(http: HttpClient) : Observable<boolean> {
    return http.delete<boolean>(`/api/v1/security/admin/manager/${this.idManager}`)
}
  ser(): any {
    return {
      idManager: this.idManager,
      shortName: this.shortName,
      fullName: this.fullName,
      position: this.position,
      email: this.email,
      phoneNumber: this.phoneNumber,
      idUser: this.idUser,
    }
  }

  save(http: HttpClient) : Observable<ResponseInfo<Manager>> {
    return http.post<ResponseInfo<Manager>>('/api/v1/security/admin/manager', this.ser())
  }
}
