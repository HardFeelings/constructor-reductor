import { EngineType } from './../models/engine';
import { Component } from '@angular/core';
import { Motor, MotorAdapterType, MotorType } from '../classes/motor';
import { Reducer } from '../classes/reducer';
import { MotorReducer } from '../classes/motor-reducer';
import { enProduct, Product } from '../classes/product';
import { ProductService } from '../sevices/product.service';
import { ProductType } from '../models/product';
import { ResponseInfo } from '../models/responesInfo';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})

export class MainComponent {
  product_selected: enProduct;
  product_enum = enProduct;
  productTypes: ProductType[];
  idProductType: number;

  constructor(private productService: ProductService) {
  }


  ngOnInit() {
    this.getAllProductTypes();
  }
  // search() {
  //   switch (this.product_selected) {
  //     case enProduct.Reducer:
  //       alert(JSON.stringify(this.reducer))
  //       break
  //     case enProduct.Motor:
  //       alert(JSON.stringify(this.motor))
  //       break
  //     case enProduct.MotorReducer:
  //       alert(JSON.stringify(this.motorReducer))
  //       break
  //   }
  // }
  getAllProductTypes(){
    this.productService.getAllProductTypes().subscribe(
      (respones: ResponseInfo<ProductType[]>) => {
        console.log("Data getAllProductTypes: ", respones.data);
        this.productTypes = respones.data;
        console.log(" this.productTypes ",  this.productTypes);
      },
      (exepcion: any) => {
        console.error("Error getAllProductTypes:", exepcion.error);
      }
    );
  }


  pickProduct(m: string, idType:number) {
    console.log(m);
    switch (m) {
      case enProduct.Motor.valueOf():
        this.product_selected = enProduct.Motor;
        this.idProductType = idType;
        break
      case enProduct.Reducer.valueOf():
        this.product_selected = enProduct.Reducer
        this.idProductType = idType;
        break
      case enProduct.MotorReducer.valueOf():
        this.product_selected = enProduct.MotorReducer
        this.idProductType = idType;
        break
    }
  }

  // isSelectedProduct(product: string): boolean {
  //   return product === this.product_selected;
  // }
}


