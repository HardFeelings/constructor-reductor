import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ABaseServiceService } from './abase-service.service';
import { ResponseInfo } from '../models/responesInfo';
import { Observable } from 'rxjs';
import { CommercialProp } from '../models/commercialProp';

@Injectable({
  providedIn: 'root'
})
export class CommercialService extends ABaseServiceService{
  private  commercialUrl = 'security/commercialProp';


  constructor(http: HttpClient) {
    super(http, 'api/v1');
  }

  getAllCommercialProps(): Observable<ResponseInfo<CommercialProp[]>> {
    return this.getwp<ResponseInfo<CommercialProp[]>>(this.commercialUrl);
  }

  getCommercialPropById(id:number): Observable<ResponseInfo<CommercialProp>> {
    return this.getwp<ResponseInfo<CommercialProp>>(`${this.commercialUrl}/${id}`);
  }

  deleteCommercialProp(id:number): Observable<ResponseInfo<Boolean>> {
    return this.delete<ResponseInfo<Boolean>>(`${this.commercialUrl}/${id}`);
  }

  saveCommercialProp(commercialProp: CommercialProp): Observable<ResponseInfo<CommercialProp>>  {
    return this.post<ResponseInfo<CommercialProp>> (`${this.commercialUrl}`, commercialProp);
  }

}
