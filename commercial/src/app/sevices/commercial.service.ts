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
  private  excelUrl = 'security/commercialProp/report';



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


  downloadBlob(blob: Blob, filename: string): void {
    const url = window.URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = filename;
    document.body.appendChild(a);
    a.click();
    window.URL.revokeObjectURL(url);
    document.body.removeChild(a);
  }


  downloadExcelById(id: number): void {
    this.http.get(`${this.endpoint}/${this.excelUrl}/${id}`, { responseType: 'blob' }).subscribe(
      (blob: Blob) => {
        this.downloadBlob(blob, `${id}.xlsx`);
      },
      (error) => {
        console.error('Error downloading the Excel file:', error);
      }
    );
}

}
