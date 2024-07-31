import { Injectable } from '@angular/core';
import { ABaseServiceService } from './abase-service.service';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Product, ProductType, ProductOption } from '../models/product';
import { ResponseInfo } from '../models/responesInfo';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService extends ABaseServiceService{
  private pruductTypeUrl = 'productType';
  private productOptionUrl = 'productOption';
  private productUrl = 'product';
  private productTypeOptionIdUrl = 'byProductTypeId';
  private filterUrl = 'filter';
  private addProsuctUrl = 'security/product/dynamicSave';
  private imageUrl = 'product/downloadImage';

  constructor(http: HttpClient) {
    super(http, 'api/v1');
  }

///////////// Product /////////////
  getAllProducts(): Observable<ResponseInfo<Product[]>> {
    return this.getwp<ResponseInfo<Product[]>>(this.productUrl);
  }

  getProductById(id:number): Observable<ResponseInfo<Product>> {
    return this.getwp<ResponseInfo<Product>>(`${this.productUrl}/${id}`);
  }

///////////// ProductType /////////////
  getAllProductTypes(): Observable<ResponseInfo<ProductType[]>> {
    return this.getwp<ResponseInfo<ProductType[]>>(this.pruductTypeUrl);
  }

  getProductTypeById(id:number): Observable<ResponseInfo<ProductType>> {
    return this.getwp<ResponseInfo<ProductType>>(`${this.pruductTypeUrl}/${id}`);
  }

///////////// ProductOption /////////////
  getAllProductOptions(): Observable<ResponseInfo<ProductOption[]>> {
    return this.getwp<ResponseInfo<ProductOption[]>>(this.productOptionUrl);
  }

  getProductOptionById(id:number): Observable<ResponseInfo<ProductOption>> {
    return this.getwp<ResponseInfo<ProductOption>>(`${this.productOptionUrl}/${id}`);
  }

///////////// ProductTypeOptionId /////////////
  getByProductTypeOptionId(id:number): Observable<ResponseInfo<ProductOption[]>> {
    return this.getwp<ResponseInfo<ProductOption[]>>(`${this.productTypeOptionIdUrl}/${id}`);
  }

  ///////////// Add  Product/////////////
  addDynamicProduct(product: Product): Observable<ResponseInfo<Product>> {
    return this.postwp<ResponseInfo<Product>>(`${this.addProsuctUrl}`, product);
  }

///////////// Image /////////////
   downloadImageById(id: number, filename: string): void {
    this.http.get(`${this.endpoint}/${this.imageUrl}/${id}`, { responseType: 'blob' }).subscribe(
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


}
