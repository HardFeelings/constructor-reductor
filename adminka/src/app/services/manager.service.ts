import { Injectable } from '@angular/core';
import { ABaseServiceService } from './abase-service.service';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Manager } from '../models/manager';
import { ResponseInfo } from '../models/responesInfo';
import { Observable } from 'rxjs';
import { Page } from '../models/page';

@Injectable({
  providedIn: 'root'
})
export class ManagerService extends ABaseServiceService{
  private  managerUrl = 'security/admin/manager';
  private  managerWithoutPageUrl = 'security/manager';


  constructor(http: HttpClient) {
    super(http, 'api/v1');
  }

  getAllManagers(): Observable<ResponseInfo<Manager[]>> {
    return this.getwp<ResponseInfo<Manager[]>>(this.managerWithoutPageUrl);
  }

  getPageManagers(offset: number): Observable<ResponseInfo<Page<Manager>>> {
    let params = new HttpParams()
        .set('offset', offset.toString())
    return this.get<ResponseInfo<Page<Manager>>>(this.managerUrl,  params);
  }

  deleteManager(id:number): Observable<ResponseInfo<Boolean>> {
    return this.delete<ResponseInfo<Boolean>>(`${this.managerUrl}/${id}`);
  }

  saveManager(manager: Manager): Observable<ResponseInfo<Manager>>  {
    return this.post<ResponseInfo<Manager>> (`${this.managerUrl}`, manager);
  }


}
