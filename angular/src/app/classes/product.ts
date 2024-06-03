import { HttpClient } from "@angular/common/http"
import { Motor } from "./motor"

export class Product {
    id: number
    productType: ProductType
    name: string
    weight: number
    price: number
    reducer: null
    motor: Motor
    options: Array<null>

    constructor() {
        this.id = 0
        this.productType = new ProductType()
        this.name = ""
        this.weight = 0
        this.price = 0
        this.reducer = null
        this.motor = new Motor()
        this.options = new Array<null>()
    }

    ser(): any {
        return {
            idProduct: this.id,
            productType: this.productType.ser(),
            name: this.name,
            weight: this.weight,
            reducer: this.reducer,
            motor: this.motor.ser(),
            options: this.options
        }
    }

    delete(http: HttpClient) {
        console.log(this)
        http.delete(`/api/v1/security/product/${this.id}`)
            .subscribe({
                next: (data: any) => {
                    console.log(data.data)
                },
                error: error => {
                    console.log(error)
                }
            });
    }

    save(http: HttpClient) {
        console.log(this)
        http.post('/api/v1/security/product', this.ser()
        ).subscribe({
            next: (data: any) => {
                console.log(data.data)
            },
            error: error => {
                console.log(error)
            }
        });
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

    delete(http: HttpClient) {
        console.log(this)
        http.delete(`/api/v1/security/productType/${this.id}`)
            .subscribe({
                next: (data: any) => {
                    console.log(data.data)
                },
                error: error => {
                    console.log(error)
                }
            });
    }

    save(http: HttpClient) {
        console.log(this)
        http.post('/api/v1/security/productType', this.ser()
        ).subscribe({
            next: (data: any) => {
                console.log(data.data)
            },
            error: error => {
                console.log(error)
            }
        });
    }
}