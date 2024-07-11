import { Injectable } from '@angular/core';
import { ABaseServiceService } from './abase-service.service';
import { HttpClient } from '@angular/common/http';
import { Reducer, ReducerAdapterType, ReducerInputType, ReducerInstallationType, ReducerMounting, ReducerType, ReducerSize, ReducerOutputShaftType } from '../models/reducer';
import { ResponseInfo } from '../models/responesInfo';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReducerService extends ABaseServiceService{
  private reductorUrl = 'reducer';
  private reducerAdapterTypeUrl = 'reducerAdapterType'
  private reducerAdapterType_byReducerTypeId = 'reducerAdapterType/byReducerTypeId';
  private reducerInputTypeUrl = 'reducerInputType';
  private reducerInputType_byReducerTypeId = 'reducerInputType/byReducerTypeId';
  private reducerInstallationType = 'reducerInstallationType';
  private reducerInstallationType_byReducerTypeId = 'reducerInstallationType/byReducerTypeId';
  private reducerMounting = 'reducerMounting';
  private reducerType = 'reducerType';
  private reducerSize = 'reducerSize';
  private reducerSize_byReducerTypeId = 'reducerSize/byReducerTypeId';
  private reducerOutputShaftType = 'reducerOutputShaftType';
  private reducerOutputShaftType_byReducerTypeId = 'reducerOutputShaftType/byReducerTypeId';

  constructor(http: HttpClient) {
    super(http, 'api/v1');
  }

///////////// Reducer /////////////
  getAllReducers(): Observable<ResponseInfo<Reducer[]>> {
    return this.getwp<ResponseInfo<Reducer[]>>(this.reductorUrl);
  }

  getReducerById(id:number): Observable<ResponseInfo<Reducer>> {
    return this.getwp<ResponseInfo<Reducer>>(`${this.reductorUrl}/${id}`);
  }

///////////// ReducerAdapterType /////////////
  getAllReducerAdapterType(): Observable<ResponseInfo<ReducerAdapterType[]>> {
    return this.getwp<ResponseInfo<ReducerAdapterType[]>>(this.reducerAdapterTypeUrl);
  }

  getReducerAdapterById(id:number): Observable<ResponseInfo<ReducerAdapterType>> {
    return this.getwp<ResponseInfo<ReducerAdapterType>>(`${this.reducerAdapterTypeUrl}/${id}`);
  }

  getReducerAdapterByReducerTypeId(id:number): Observable<ResponseInfo<ReducerAdapterType[]>> {
    return this.getwp<ResponseInfo<ReducerAdapterType[]>>(`${this.reducerAdapterType_byReducerTypeId}/${id}`);
  }

///////////// ReducerInputType /////////////
  getAllReducerInputType(): Observable<ResponseInfo<ReducerInputType[]>> {
    return this.getwp<ResponseInfo<ReducerInputType[]>>(this.reducerInputTypeUrl);
  }

  getReducerInputById(id:number): Observable<ResponseInfo<ReducerInputType>> {
    return this.getwp<ResponseInfo<ReducerInputType>>(`${this.reducerInputTypeUrl}/${id}`);
  }

  getReducerInputByReducerTypeId(id:number): Observable<ResponseInfo<ReducerInputType[]>> {
    return this.getwp<ResponseInfo<ReducerInputType[]>>(`${this.reducerInputType_byReducerTypeId}/${id}`);
  }

///////////// ReducerInstallationType /////////////
  getAllReducerInstallationType(): Observable<ResponseInfo<ReducerInstallationType[]>> {
    return this.getwp<ResponseInfo<ReducerInstallationType[]>>(this.reducerInstallationType);
  }

  getReducerInstallationById(id:number): Observable<ResponseInfo<ReducerInstallationType>> {
    return this.getwp<ResponseInfo<ReducerInstallationType>>(`${this.reducerInstallationType}/${id}`);
  }

  getReducerInstallationByReducerTypeId(id:number): Observable<ResponseInfo<ReducerInstallationType[]>> {
    return this.getwp<ResponseInfo<ReducerInstallationType[]>>(`${this.reducerInstallationType_byReducerTypeId}/${id}`);
  }

///////////// MountingPoint /////////////
  getAllReducerMounting(): Observable<ResponseInfo<ReducerMounting[]>> {
    return this.getwp<ResponseInfo<ReducerMounting[]>>(this.reducerMounting);
  }

  getReducerMountingById(id:number): Observable<ResponseInfo<ReducerMounting>> {
    return this.getwp<ResponseInfo<ReducerMounting>>(`${this.reducerMounting}/${id}`);
  }

///////////// ReducerType /////////////
  getAllReducerTypes(): Observable<ResponseInfo<ReducerType[]>> {
    return this.getwp<ResponseInfo<ReducerType[]>>(this.reducerType);
  }

  getReducerTypeById(id:number): Observable<ResponseInfo<ReducerType>> {
    return this.getwp<ResponseInfo<ReducerType>>(`${this.reducerType}/${id}`);
  }

///////////// ReducerSize /////////////
  getAllReducerSizes(): Observable<ResponseInfo<ReducerSize[]>> {
    return this.getwp<ResponseInfo<ReducerSize[]>>(this.reducerSize);
  }

  getReducerSizeById(id:number): Observable<ResponseInfo<ReducerSize>> {
    return this.getwp<ResponseInfo<ReducerSize>>(`${this.reducerSize}/${id}`);
  }

  getReducerSizeByReducerTypeId(id:number): Observable<ResponseInfo<ReducerSize[]>> {
    return this.getwp<ResponseInfo<ReducerSize[]>>(`${this.reducerSize_byReducerTypeId}/${id}`);
  }

///////////// ReducerOutputShaftType /////////////
  getAllReducerOutputShaftTypes(): Observable<ResponseInfo<ReducerOutputShaftType[]>> {
    return this.getwp<ResponseInfo<ReducerOutputShaftType[]>>(this.reducerOutputShaftType);
  }

  getReducerOutputShaftTypeById(id:number): Observable<ResponseInfo<ReducerOutputShaftType>> {
    return this.getwp<ResponseInfo<ReducerOutputShaftType>>(`${this.reducerOutputShaftType}/${id}`);
  }

  getReducerOutputShaftTypeByReducerTypeId(id:number): Observable<ResponseInfo<ReducerOutputShaftType[]>> {
    return this.getwp<ResponseInfo<ReducerOutputShaftType[]>>(`${this.reducerOutputShaftType_byReducerTypeId}/${id}`);
  }


}
