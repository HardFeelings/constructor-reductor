import { Injectable } from '@angular/core';
import { ABaseServiceService } from './abase-service.service';
import { HttpClient } from '@angular/common/http';
import { Manager } from '../models/manager';
import { ResponseInfo } from '../models/responesInfo';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ManagerService extends ABaseServiceService{
  private  managerUrl = 'security/manager';


  constructor(http: HttpClient) {
    super(http, 'api/v1');
  }

  getAllManagers(): Observable<ResponseInfo<Manager[]>> {
    return this.getwp<ResponseInfo<Manager[]>>(this.managerUrl);
  }

  deleteManager(id:number): Observable<ResponseInfo<Boolean>> {
    return this.delete<ResponseInfo<Boolean>>(`${this.managerUrl}/${id}`);
  }

  saveManager(manager: Manager): Observable<ResponseInfo<Manager>>  {
    return this.post<ResponseInfo<Manager>> (`${this.managerUrl}`, manager);
  }


}
