import { Injectable } from '@angular/core';
import { ABaseServiceService } from './abase-service.service';
import { HttpClient } from '@angular/common/http';
import { Product, ProductType, ProductOption } from '../models/product';
import { ResponseInfo } from '../models/responesInfo';
import { Observable } from 'rxjs';
import { Filter } from '../models/filter';

@Injectable({
  providedIn: 'root'
})
export class ProductService extends ABaseServiceService{
  private pruductTypeUrl = 'productType';
  private productOptionUrl = 'productOption';
  private productUrl = 'product';
  private productTypeOptionIdUrl = 'byProductTypeId';
  private filterUrl = 'filter';

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

///////////// Product  Filter/////////////
  postFilter(filter: Filter): Observable<ResponseInfo<Product[]>> {
    return this.postwp<ResponseInfo<Product[]>>(`${this.filterUrl}`, filter);
  }

}
