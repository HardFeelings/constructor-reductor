import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Motor, MotorAdapterType, MotorType } from '../classes/motor';
import { FormsModule } from '@angular/forms';
import { Product, ProductType } from '../classes/product';

@Component({
  selector: 'app-adminka',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './adminka.component.html',
  styleUrl: './adminka.component.scss'
})
export class AdminkaComponent {

  id = 0
  // make  motor_list type of array of motors
  motor_list: Motor[]
  motorType_list: MotorType[]
  motorAdapterType_list: MotorAdapterType[]
  product_list: Product[]

  constructor(private http: HttpClient) {
    this.motor_list = new Array<Motor>();
    this.motorType_list = new Array<MotorType>();
    this.motorAdapterType_list = new Array<MotorAdapterType>();
    this.product_list = new Array<Product>();
  }

  setid(i: number) {
    this.id = i
  }

  getHttp() {
    return this.http
  }

  ngOnInit() {
    this.getMotorList()
    this.getMotorTypeList()
    this.getMotorAdapterTypeList()
    this.getProductList()
  }

  addMotor() {
    var motor = new Motor()
    console.log(motor)
    this.motor_list.push(motor)
  }

  addMotorType() {
    var motorType = new MotorType()
    console.log(motorType)
    this.motorType_list.push(motorType)
  }

  addMotorAdapterType() {
    var motorAdapterType = new MotorAdapterType()
    console.log(motorAdapterType)
    this.motorAdapterType_list.push(motorAdapterType)
  }

  addProduct() {
    var product = new Product()
    console.log(product)
    this.product_list.push(product)
  }

  getMotorList() {
    this.http.get('/api/v1/motor').subscribe({
      next: (data: any) => {
        data.data.forEach((e: { [x: string]: any; }) => {
          var motor = new Motor()
          motor.id = e["idMotor"]
          motor.frequency.value = e["frequency"]
          motor.adapterType.id = e["motorAdapterTypeId"]
          motor.power = e["power"]
          motor.rpm.value = e["rpm"]
          motor.type.id = e["motorTypeId"]
          this.motor_list.push(motor)
        })
      },
      error: error => { console.log(error); }
    });
  }

  getProductList() {
    this.http.get('/api/v1/product').subscribe({
      next: (data: any) => {
        data.data.forEach((e: { [x: string]: any; }) => {
          var product = new Product()
          product.id = e["idProduct"]
          product.productTypeId = e["productTypeId"]
          product.name = e["name"]
          product.weight = e["weight"]
          product.price = e["price"]
          product.reducerId = e["reducerId"]
          product.motorId = e["motorId"]
          product.optionsIds = e["optionsIds"]
          product.optionsString = product.optionsIds.join(',')
          this.product_list.push(product)
        })
      },
      error: error => { console.log(error); }
    });
  }


  getMotorTypeList() {
    this.http.get('/api/v1/motorType').subscribe({
      next: (data: any) => {
        data.data.forEach((e: { [x: string]: any; }) => {
          var motorType = new MotorType()
          motorType.id = e["idMotorType"]
          motorType.value = e["motorTypeName"]
          this.motorType_list.push(motorType)
        })
      },
      error: error => { console.log(error); }
    });
  }

  getMotorAdapterTypeList() {
    this.http.get('/api/v1/motorAdapterType').subscribe({
      next: (data: any) => {
        data.data.forEach((e: { [x: string]: any; }) => {
          var motorAdapterType = new MotorAdapterType()
          motorAdapterType.id = e["idMotorAdapterType"]
          motorAdapterType.value = e["motorAdapterTypeValue"]
          motorAdapterType.typeid = e["motorTypeId"]
          this.motorAdapterType_list.push(motorAdapterType)
        })
      },
      error: error => { console.log(error); }
    });
  }
}
