import { HttpClient } from "@angular/common/http"
import { Observable } from 'rxjs';

export class Product {
    id: number
    productTypeId: number
    name: string
    weight: number
    price: number
    reducerId: number | null
    motorId: number | null
    optionsIds: number[]| null;
    optionsString: string | null;
    imageEmpty!: boolean;
    imageString: string | null;
    imageChanged!: boolean;
    rpm!: number;
    torqueMoment!:number;
    serviceFactor!: number;


    ser(): any {
        return {
            idProduct: this.id,
            productTypeId: this.productTypeId,
            name: this.name,
            weight: this.weight,
            price: this.price,
            reducerId: this.reducerId,
            motorId: this.motorId,
            imageEmpty: this.imageEmpty,
            imageString: this.imageString,
            imageChanged: this.imageChanged,
            rpm: this.rpm,
            torqueMoment: this.torqueMoment,
            serviceFactor: this.serviceFactor,
            optionsIds: this.optionsString ? this.optionsString.split(',').map(item => parseInt(item, 10)) : []
        }
    }

    delete(http: HttpClient) : Observable<boolean>{
        // console.log(this)
        return http.delete<boolean>(`/api/v1/security/product/${this.id}`)
    }

    save(http: HttpClient): Observable<Product>{
      // console.log(this.ser())
     return http.post<Product>('/api/v1/security/product', this.ser())
  }
}


export class ProductType {
    id: number
    value: string

    constructor() {
        this.id = 0
        this.value = ""
    }

    ser(): any {
        return {
            idProductType: this.id,
            productTypeValue: this.value
        }
    }

    public static getAll(http: HttpClient): Array<ProductType> {
        var list: ProductType[] = new Array<ProductType>()
        http.get('/api/v1/productType')
            .subscribe({
                next: (data: any) => {
                    // console.log("get all")
                    data.data.forEach((e: { [x: string]: any; }) => {
                        var productList = new ProductType()
                        productList.id = e["idProductType"]
                        productList.value = e["productTypeValue"]
                        list.push(productList)
                    })
                },
                error: error => {
                    console.log(error)
                }
            });
        return list;
    }

    delete(http: HttpClient) : Observable<boolean>{
       return http.delete<boolean>(`/api/v1/security/productType/${this.id}`)

    }

    save(http: HttpClient): Observable<any>{
        return http.post<any>('/api/v1/security/productType', this.ser())
    }
}


export class ProductOption {
    idProductOption: number
    productOptionValue: string
    productTypeId: number

    constructor() {
        this.idProductOption = 0
        this.productOptionValue = ""
        this.productTypeId = 0

    }

    ser(): any {
        return {
            idProductOption: this.idProductOption,
            productOptionValue: this.productOptionValue,
            productTypeId: this.productTypeId
        }
    }

    delete(http: HttpClient) : Observable<boolean>{
       return http.delete<boolean>(`/api/v1/security/productOption/${this.idProductOption}`)
    }

    save(http: HttpClient): Observable<ProductOption> {
    return http.post<ProductOption>('/api/v1/security/productOption', this.ser())
    }

}

  export enum enProduct {
    Motor = "Двигатель",
    Reducer = "Редуктор",
    MotorReducer = "Мотор-редуктор"
  }

