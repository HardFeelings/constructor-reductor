import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Motor, MotorAdapterType, MotorType } from '../classes/motor';
import { FormsModule } from '@angular/forms';
import { Product, ProductOption, ProductType } from '../classes/product';
import { MountingPoint, Reducer, ReducerAdapterType, ReducerInputType, ReducerInstallationType, ReducerOutputShaftType, ReducerSize, ReducerType } from '../classes/reducer';

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
  productType_list: ProductType[]
  productOption_list: ProductOption[]
  reducer_list: Reducer[]
  reducerType_list: ReducerType[]
  reducerSize_list: ReducerSize[]
  reducerOutput_list: ReducerOutputShaftType[]
  reducerMounting_list: MountingPoint[]
  reducerInstallationType_list: ReducerInstallationType[]
  reducerInputType_list: ReducerInputType[]
  reducerAdapterType_list: ReducerAdapterType[]

  constructor(private http: HttpClient) {
    this.motor_list = new Array<Motor>();
    this.motorType_list = new Array<MotorType>();
    this.motorAdapterType_list = new Array<MotorAdapterType>();
    this.product_list = new Array<Product>();
    this.productType_list = new Array<ProductType>();
    this.productOption_list = new Array<ProductOption>();
    this.reducer_list = new Array<Reducer>();
    this.reducerType_list = new Array<ReducerType>();
    this.reducerSize_list = new Array<ReducerSize>();
    this.reducerOutput_list = new Array<ReducerOutputShaftType>();
    this.reducerMounting_list = new Array<MountingPoint>();
    this.reducerInstallationType_list = new Array<ReducerInstallationType>();
    this.reducerInputType_list = new Array<ReducerInputType>();
    this.reducerAdapterType_list = new Array<ReducerAdapterType>();
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
    this.getProductType()
    this.getProductOption()
    this.getReducer()
    this.getReducerSize()
    this.getReducerType()
    this.getReducerOutputShaft()
    this.getReducerMounting()
    this.getReducerInstallationType()
    this.getReducerInputType()
    this.getReducerAdapterType()
  }

  addMotor() {
    var motor = new Motor()
    this.motor_list.push(motor)
  }

  addMotorType() {
    var motorType = new MotorType()
    this.motorType_list.push(motorType)
  }

  addMotorAdapterType() {
    var motorAdapterType = new MotorAdapterType()
    this.motorAdapterType_list.push(motorAdapterType)
  }

  addProduct() {
    var product = new Product()
    this.product_list.push(product)
  }

  getProductType() {
    this.productType_list = ProductType.getAll(this.http)
  }

saveProductType(productType: ProductType) {
  productType.save(this.http).subscribe(() => {
    this.getProductType()
  });
}

  addProductType() {
    var productType = new ProductType()
    this.productType_list.push(productType)
  }

  deleteProductType(i: ProductType) {
    i.delete(this.http)
    this.getProductType()
  }

  getProductOption() {
    this.productOption_list = ProductOption.getAll(this.http)
  }

  saveProductOptions(i: ProductOption) {
    i.save(this.http).subscribe(() => {
      this.getProductOption()
    });
  }

  addProductOption() {
    var productOption = new ProductOption()
    this.productOption_list.push(productOption)
  }

  deleteProductOption(i: ProductOption) {
    i.delete(this.http)
    this.getProductOption()
  }


  getReducer() {
    this.reducer_list = Reducer.getAll(this.http)
  }

  saveReducer(i: Reducer) {
    i.save(this.http).subscribe(() => {
      this.getReducer()
    });
  }

  addReducer() {
    var reducer = new Reducer()
    this.reducer_list.push(reducer)
  }

  deleteReducer(i: Reducer) {
    i.delete(this.http)
    this.getReducer()
  }


  getReducerType() {
    this.reducerType_list = ReducerType.getAll(this.http)
  }

  saveReducerType(i: ReducerType) {
    i.save(this.http).subscribe(() => {
      this.getReducerType();
    })
  }

  addReducerType() {
    var reducerType = new ReducerType()
    this.reducerType_list.push(reducerType)
  }

  deleteReducerType(i: ReducerType) {
    i.delete(this.http)
    this.getReducerType()
  }


  getReducerSize() {
    this.reducerSize_list = ReducerSize.getAll(this.http)
  }

  saveReducerSize(i: ReducerSize) {
    i.save(this.http).subscribe(() => {
      this.getReducerSize()
    });
  }

  addReducerSize() {
    var reducerSize = new ReducerSize()
    this.reducerSize_list.push(reducerSize)
  }

  deleteReducerSize(i: ReducerSize) {
    i.delete(this.http)
    this.getReducerSize()
  }


  getReducerOutputShaft() {
    this.reducerOutput_list = ReducerOutputShaftType.getAll(this.http)
  }

  saveReducerOutputShaft(i: ReducerOutputShaftType) {
    i.save(this.http).subscribe(() => {
      this.getReducerOutputShaft()
    });
  }

  addReducerOutputShaft() {
    var reducerOutputShaftType = new ReducerOutputShaftType()
    this.reducerOutput_list.push(reducerOutputShaftType)
  }

  deleteReducerOutputShaft(i: ReducerOutputShaftType) {
    i.delete(this.http)
    this.getReducerOutputShaft()
  }


  getReducerMounting() {
    this.reducerMounting_list = MountingPoint.getAll(this.http)
  }

  saveReducerMounting(i: MountingPoint) {
    i.save(this.http).subscribe(() => {
      this.getReducerMounting()
    });
  }

  addReducerMounting() {
    var mountingPoint = new MountingPoint()
    this.reducerMounting_list.push(mountingPoint)
  }

  deleteReducerMounting(i: MountingPoint) {
    i.delete(this.http)
    this.getReducerMounting()
  }



  getReducerInstallationType() {
    this.reducerInstallationType_list = ReducerInstallationType.getAll(this.http)
  }

  saveReducerInstallationType(i: ReducerInstallationType) {
    i.save(this.http).subscribe(() => {
      this.getReducerInstallationType()
    });
  }

  addReducerInstallationType() {
    var reducerInstallationType = new ReducerInstallationType()
    this.reducerInstallationType_list.push(reducerInstallationType)
  }

  deleteReducerInstallationType(i: ReducerInstallationType) {
    i.delete(this.http)
    this.getReducerInstallationType()
  }


  getReducerInputType() {
    this.reducerInputType_list = ReducerInputType.getAll(this.http)
  }

  saveReducerInputType(i: ReducerInputType){
    i.save(this.http).subscribe(() => {
      this.getReducerInputType()
    });
  }

  addReducerInputType() {
    var reducerInputType = new ReducerInputType()
    this.reducerInputType_list.push(reducerInputType)
  }

  deleteReducerInputType(i: ReducerInputType) {
    i.delete(this.http)
    this.getReducerInputType()
  }


  getReducerAdapterType() {
    this.reducerAdapterType_list = ReducerAdapterType.getAll(this.http)
  }

  saveReducerIAdapterType(i: ReducerAdapterType) {
    i.save(this.http).subscribe(() => {
      this.getReducerAdapterType()
    });
  }

  addReducerAdapterType() {
    var reducerAdapterType = new ReducerAdapterType()
    this.reducerAdapterType_list.push(reducerAdapterType)
  }

  deleteReducerAdapterType(i: ReducerAdapterType) {
    i.delete(this.http)
    this.getReducerInputType()
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
