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
  idUser: number;

}
