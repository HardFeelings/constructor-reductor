
import { Component } from '@angular/core';
import { ProductService } from '../sevices/product.service';
import { ProductType, enProduct } from '../models/product';
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

  getAllProductTypes(){
    this.productService.getAllProductTypes().subscribe(
      (respones: ResponseInfo<ProductType[]>) => {
        if(respones.data !== null){
          console.log("Data getAllProductTypes: ", respones.data);
          this.productTypes = respones.data;
          console.log(" this.productTypes ",  this.productTypes);
        } else {
          alert(JSON.stringify(respones.errorMsg))
        }
      }
      // (exepcion: any) => {
      //   console.error("Error getAllProductTypes:", exepcion.error);
      // }
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

}


