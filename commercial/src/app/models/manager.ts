import { HttpClient } from "@angular/common/http";
import { Observable } from 'rxjs';
import { ResponseInfo } from "./responesInfo";

export class Manager {
  idManager: number;
  shortName: string | null;
  fullName: string;
  position: string;
  email: string;
  phoneNumber: string;

  constructor(idManager: number, shortName: string, fullName:string,position:string,email: string, phoneNumber: string ) {
    this.idManager = idManager;
    this.shortName = shortName;
    this.fullName = fullName;
    this.position = position;
    this.email = email;
    this.phoneNumber = phoneNumber;
  }
}

//   delete(http: HttpClient) : Observable<boolean> {
//     return http.delete<boolean>(`/api/v1/security/manager/${this.idManager}`)
// }
//   ser(): any {
//     return {
//       idManager: this.idManager,
//       shortName: this.shortName,
//       fullName: this.fullName,
//       position: this.position,
//       email: this.email,
//       phoneNumber: this.phoneNumber,
//     }
//   }

//   save(http: HttpClient) : Observable<ResponseInfo<Manager>> {
//     return http.post<ResponseInfo<Manager>>('/api/v1/security/manager', this.ser())
//   }

