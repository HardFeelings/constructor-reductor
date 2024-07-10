import { Engine, EngineType } from './../models/engine';
import { MotorService } from '../sevices/motor.service';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from "@angular/router";
import { Motor, MotorAdapterType, MotorType } from '../classes/motor';
import { Reducer } from '../classes/reducer';
import { MotorReducer } from '../classes/motor-reducer';
import { enProduct, Product } from '../classes/product';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})

export class MainComponent {
  product_variants = Object.values(enProduct);
  product_selected: enProduct | null = null;
  product_enum = enProduct;
  allEngineTypes: EngineType[];

  motor: Motor;
  reducer: Reducer;
  motorReducer: MotorReducer;

  constructor() {
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

  pickProduct(m: string) {
    switch (m) {
      case enProduct.Motor.valueOf():
        this.product_selected = enProduct.Motor;
        break
      case enProduct.Reducer.valueOf():
        this.product_selected = enProduct.Reducer
        break
      case enProduct.MotorReducer.valueOf():
        this.product_selected = enProduct.MotorReducer
        break
    }
  }

  isSelectedProduct(product: string): boolean {
    return product === this.product_selected;
  }
}


