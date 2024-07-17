import { Injectable } from '@angular/core';
import { ABaseServiceService } from './abase-service.service';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Engine, EngineAdapterType, EngineType } from '../models/engine';
import { ResponseInfo } from '../models/responesInfo';

@Injectable({
  providedIn: 'root'
})
export class MotorService extends ABaseServiceService{
  private  motorUrl = 'motor';
  private motorTypeUrl = 'motorType';
  private motorAdapterTypeUrl = 'motorAdapterType';
  private motorAdapterType_byMotorTypeIdURL = 'motorAdapterType/byMotorTypeId';

  constructor(http: HttpClient) {
    super(http, 'api/v1');
  }

///////////// Engine /////////////
  getAllMotor(): Observable<ResponseInfo<Engine[]>> {
    return this.getwp<ResponseInfo<Engine[]>>(this.motorUrl);
  }

  getMotorById(id:number): Observable<ResponseInfo<Engine>> {
    return this.getwp<ResponseInfo<Engine>>(`${this.motorUrl}/${id}`);
  }

///////////// EngineType /////////////
  getAllMotorType(): Observable<ResponseInfo<EngineType[]>> {
    return this.getwp<ResponseInfo<EngineType[]>>(this.motorTypeUrl);
  }

  getMotorTypeById(id:number): Observable<ResponseInfo<EngineType>> {
    return this.getwp<ResponseInfo<EngineType>>(`${this.motorTypeUrl}/${id}`);
  }

///////////// EngineAdapterType /////////////
  getAllMotorAdapterType(): Observable<ResponseInfo<EngineAdapterType[]>> {
    return this.getwp<ResponseInfo<EngineAdapterType[]>>(this.motorAdapterTypeUrl);
  }

  getMotorAdapterById(id:number): Observable<ResponseInfo<EngineAdapterType>> {
    return this.getwp<ResponseInfo<EngineAdapterType>>(`${this.motorAdapterTypeUrl}/${id}`);
  }

  getMotorAdapterByMotorTypeId(id:number): Observable<ResponseInfo<EngineAdapterType[]>> {
    return this.getwp<ResponseInfo<EngineAdapterType[]>>(`${this.motorAdapterType_byMotorTypeIdURL}/${id}`);
  }



}
