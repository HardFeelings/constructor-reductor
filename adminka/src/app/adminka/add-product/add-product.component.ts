import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Product, ProductType, enProduct } from 'src/app/models/product';
import { ResponseInfo } from 'src/app/models/responesInfo';
import { ProductService } from 'src/app/services/product.service';


@Component({
  selector: 'app-add-product',
  // standalone: true,
  // imports: [],
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.scss']
})
export class AddProductComponent {
  newProductList: Product[] = [];
  product_selected: enProduct;
  product_enum = enProduct;
  productTypes: ProductType[];
  idProductType: number;
  selectedButton: number | null = null;

  constructor(public dialogRef: MatDialogRef<AddProductComponent>,
    @Inject(MAT_DIALOG_DATA) public data:any,
    private productService: ProductService){

  }


  ngOnInit() {
    this.getAllProductTypes();
  }

  getAllProductTypes(){
    this.productService.getAllProductTypes().subscribe((respones: ResponseInfo<ProductType[]>) => {
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

  addProduct(event: Product) {
    this.productService.addDynamicProduct(event).subscribe(
        (response: ResponseInfo<Product>) => {
            console.log("Data addProduct: ", response);
            if (response.data !== null) {
                console.log("Data addProduct: ", response.data);
                this.newProductList.push(response.data);
            }
        },
        error => {
          let errorMessage = error.error?.errorMsg || 'Неизвестная ошибка';
          alert(errorMessage);
        }
    );
}


  downloadImage(id:number,filename: string){
    this.productService.downloadImageById(id,filename);
  }

  pickProduct(m: string, idType:number) {
    console.log(m);
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


  close(): void {
    this.dialogRef.close(this.newProductList);
  }

}
