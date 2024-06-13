import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from "@angular/router";
import { Motor, MotorAdapterType, MotorType } from '../classes/motor';
import { Reducer } from '../classes/reducer';
import { MotorReducer } from '../classes/motor-reducer';

@Component({
  selector: 'app-main',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './main.component.html',
  styleUrl: './main.component.scss'
})

export class MainComponent {
  product_variants = Object.values(Product)
  product_selected: Product | null = null
  product_enum = Product

  motor: Motor
  reducer: Reducer
  motorReducer: MotorReducer

  constructor(private http: HttpClient) {
    this.motor = new Motor()
    this.reducer = new Reducer()
    this.motorReducer = new MotorReducer()
  }

  ngOnInit() {
    // this.http.get('/api/v1/reducerType').subscribe({
    //   next: (data: any) => {
    //     console.log(data.data)
    //     this.items1 = data.data
    //   },
    //   error: error => { console.log(error); }
    // });
    // this.http.get('/api/v1/inputNode').subscribe({
    //   next: (data: any) => {
    //     console.log(data.data)
    //     this.items2 = data.data
    //   },
    //   error: error => { console.log(error) }
    // });
  }

  search() {
    switch (this.product_selected) {
      case Product.Reducer:
        alert(JSON.stringify(this.reducer))
        break
      case Product.Motor:
        alert(JSON.stringify(this.motor))
        break
      case Product.MotorReducer:
        alert(JSON.stringify(this.motorReducer))
        break
    }
  }

  pickProduct(m: string) {
    switch (m) {
      case Product.Motor.valueOf():
        this.product_selected = Product.Motor
        break
      case Product.Reducer.valueOf():
        this.product_selected = Product.Reducer
        break
      case Product.MotorReducer.valueOf():
        this.product_selected = Product.MotorReducer
        break
    }
  }

  isSelectedProduct(product: string): boolean {
    return product === this.product_selected;
  }
}

enum Product {
  Motor = "Двигатель",
  Reducer = "Редуктор",
  MotorReducer = "Мотор-редуктор"
}



