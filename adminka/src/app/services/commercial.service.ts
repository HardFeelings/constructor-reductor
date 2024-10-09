import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ABaseServiceService } from './abase-service.service';
import { ResponseInfo } from '../models/responesInfo';
import { Observable } from 'rxjs';
import { CommercialProp } from '../models/commercialProp';
import { PaymentTerms } from '../models/paymentTerm';
import { Page } from '../models/page';
import { catchError, timeout } from 'rxjs/operators';
import { throwError } from 'rxjs';
import * as XLSX from 'xlsx';
import { jsPDF } from 'jspdf';

@Injectable({
  providedIn: 'root'
})
export class CommercialService extends ABaseServiceService{
  private  commercialUrl = 'security/commercialProp';
  private  excelUrl = 'security/commercialProp/report';
  private  pdfUrl = 'security/commercialProp/reportPdf';
  private filterUrl = 'security/commercialProp/getByFilter';
  private paymentUrl = 'security/paymentTerms';

  constructor(http: HttpClient) {
    super(http, 'api/v1');
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

  filterProp(commercialProp: CommercialProp): Observable<ResponseInfo<CommercialProp[]>>  {
    return this.post<ResponseInfo<CommercialProp[]>> (`${this.filterUrl}`, commercialProp);
  }

  filterPageProp(commercialProp: CommercialProp, offset: number): Observable<ResponseInfo<Page<CommercialProp>>> {
    let params = new HttpParams()
        .set('offset', offset.toString())
    return this.post<ResponseInfo<Page<CommercialProp>>>(this.filterUrl, commercialProp,  params);
  }

  getPaymentTerms(): Observable<ResponseInfo<PaymentTerms[]>> {
    return this.getwp<ResponseInfo<PaymentTerms[]>>(this.paymentUrl);
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


  downloadExcelById(comm: CommercialProp): void {
    this.http.get(`${this.endpoint}/${this.excelUrl}/${comm.idCommercialProp}`, { responseType: 'blob' }).subscribe(
      (blob: Blob) => {
        this.downloadBlob(blob, `КП${comm.number}-${comm.manager?.shortName}-${comm.partner}.xlsx`);
      },
      (error) => {
        //console.error('Error downloading the Excel file:', error);
      }
    );
  }

  downloadPdf(comm: CommercialProp): void {
    this.http.get(`${this.endpoint}/${this.excelUrl}/${comm.idCommercialProp}`, { responseType: 'blob' }).subscribe(
      (blob: Blob) => {
        const reader = new FileReader();

        reader.onload = (e) => {
          const data = new Uint8Array(reader.result as ArrayBuffer);
          const workbook = XLSX.read(data, { type: 'array' });


          const worksheet = workbook.Sheets[workbook.SheetNames[0]];
          const json = XLSX.utils.sheet_to_json(worksheet, { header: 1 });


          this.createPDF(json, `КП${comm.number}-${comm.manager?.shortName}-${comm.partner}.pdf`);
        };

        reader.readAsArrayBuffer(blob);
      },
      (error) => {
        //console.error('Error downloading the Excel file:', error);
      }
    );
  }

  private createPDF(data: any[], filename: string): void {
    const doc = new jsPDF();
    data.forEach((row, index) => {
      doc.text(row.join(' '), 10, 10 + (index * 10));
    });

    doc.save(filename);
  }


  // downloadPdfById(comm: CommercialProp): void {
  //   this.http.get(`${this.endpoint}/${this.pdfUrl}/${comm.idCommercialProp}`, { responseType: 'blob' }).subscribe(

  //     (blob: Blob) => {
  //       this.downloadBlob(blob, `КП${comm.number}-${comm.manager?.shortName}-${comm.partner}.pdf`);
  //     },
  //     (error) => {
  //       console.error('Error downloading the Pdf file:', error);
  //     }
  //   );
  // }
  downloadPdfById(comm: CommercialProp): void {
    this.http.get(`${this.endpoint}/${this.pdfUrl}/${comm.idCommercialProp}`, { responseType: 'blob' }).pipe(
      timeout(300),
      catchError((error) => {
        if (error.name === 'TimeoutError') {
          //console.error('Запрос превысил время ожидания.');
        }
        alert('Error downloading the Pdf file');
        return throwError(error);
      })
    ).subscribe(
      (blob: Blob) => {
        this.downloadBlob(blob, `КП${comm.number}-${comm.manager?.shortName}-${comm.partner}.pdf`);
      },
      (error) => {
      }
    );
  }
}
