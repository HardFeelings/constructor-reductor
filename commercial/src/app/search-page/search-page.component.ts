import { Component } from '@angular/core';
import { ProductService } from '../sevices/product.service';
import { ProductType, enProduct } from '../models/product';
import { ResponseInfo } from '../models/responesInfo';


@Component({
  selector: 'app-search-page',
  templateUrl: './search-page.component.html',
  styleUrls: ['./search-page.component.scss']
})
export class SearchPageComponent {
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

}


