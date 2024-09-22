import { Injectable } from '@angular/core';
import { ABaseServiceService } from './abase-service.service';
import { HttpClient, HttpParams } from '@angular/common/http';
import { ResponseInfo } from '../models/responesInfo';
import { Observable } from 'rxjs';
import { Product } from '../classes/product';
import { Page } from '../models/page';


@Injectable({
  providedIn: 'root'
})
export class ImageService extends ABaseServiceService {
  private imageUrl = 'api/v1/security/product/downloadImage';
  private searchUrl = 'security/product/getByName';

  constructor(http: HttpClient) {
    super(http, 'api/v1');
  }

  downloadImageById(id: number, filename: string): void {
    this.http.get(`${this.imageUrl}/${id}`, { responseType: 'blob' }).subscribe(
      (blob: Blob) => {
        this.downloadBlob(blob, `${filename}.jpg`);
      },
      (error) => {
        console.error('Error downloading the image:', error);
      }
    );
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

  ///////////// SearchProduct /////////////
  searchProduct(name: string | null): Observable<ResponseInfo<Product[]>> {
    let params = new HttpParams().set('name', name ?? '');
    return this.get<ResponseInfo<Product[]>>(this.searchUrl, params);
  }

  getPagesearchProduct(offset: number, name: string | null): Observable<ResponseInfo<Page<Product>>> {
    let params = new HttpParams()
        .set('offset', offset.toString())
        .set('name', name ?? '');
    return this.get<ResponseInfo<Page<Product>>>(this.searchUrl,  params);
}
}
