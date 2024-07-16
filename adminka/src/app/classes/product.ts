import { HttpClient } from "@angular/common/http"
import { Motor } from "./motor"
import { Observable } from 'rxjs';

export class Product {
    id: number
    productTypeId: number
    name: string
    weight: number
    price: number
    reducerId: number | null
    motorId: number | null
    optionsIds: Array<number>
    optionsString: string
    imageEmpty!: boolean;
    imageString: string | null;

    // constructor() {
    //     this.id = 0
    //     this.productTypeId = 0
    //     this.name = ""
    //     this.weight = 0
    //     this.price = 0
    //     this.reducerId = null
    //     this.motorId = null
    //     this.optionsIds = new Array<number>()
    //     this.optionsString = ""
    // }

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
            optionsIds: this.optionsString == "" ? new Array<number>() : this.optionsString.split(',').map(function (item) {
                return parseInt(item, 10);
            })
        }
    }



    delete(http: HttpClient) : Observable<boolean>{
        console.log(this)
        return http.delete<boolean>(`/api/v1/security/product/${this.id}`)
    }

    save(http: HttpClient): Observable<Product> {
        console.log(this.ser())
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
                    console.log("get all")
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

    public static getAll(http: HttpClient): Array<ProductOption> {
        var list: ProductOption[] = new Array<ProductOption>()
        http.get('/api/v1/productOption')
            .subscribe({
                next: (data: any) => {
                    console.log("get all")
                    data.data.forEach((e: { [x: string]: any; }) => {
                        var productOption = new ProductOption()
                        productOption.idProductOption = e["idProductOption"]
                        productOption.productOptionValue = e["productOptionValue"]
                        productOption.productTypeId = e["productTypeId"]
                        list.push(productOption)
                    })
                },
                error: error => {
                    console.log(error)
                }
            });
        return list;
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

