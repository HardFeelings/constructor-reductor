import { Component } from '@angular/core';
import { ProductService } from '../sevices/product.service';
import { ProductType, enProduct } from '../models/product';
import { ResponseInfo } from '../models/responesInfo';
import { NGXLogger } from "ngx-logger";

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
  selectedButton: number | null = null;

  constructor(private productService: ProductService, private logger: NGXLogger) {
  }


  ngOnInit() {
    this.getAllProductTypes();
  }

  getAllProductTypes(){
    this.productService.getAllProductTypes().subscribe((respones: ResponseInfo<ProductType[]>) => {
        if(respones.data !== null){
          this.logger.log("Data getAllProductTypes: ", respones.data);
          this.productTypes = respones.data;
          this.logger.log(" this.productTypes ",  this.productTypes);
        } else {
          alert(JSON.stringify(respones.errorMsg))
        }
      }
    );
  }


  pickProduct(m: string, idType:number) {
    this.logger.log(m);
    switch (m) {
      case enProduct.Motor.valueOf():
        this.product_selected = enProduct.Motor;
        this.idProductType = idType;
        this.selectedButton = idType;
        break
      case enProduct.Reducer.valueOf():
        this.product_selected = enProduct.Reducer
        this.idProductType = idType;
        this.selectedButton = idType;
        break
      case enProduct.MotorReducer.valueOf():
        this.product_selected = enProduct.MotorReducer
        this.idProductType = idType;
        this.selectedButton = idType;
        break
    }
  }

}


