import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { NGXLogger } from "ngx-logger";
import { Page } from 'src/app/models/page';
import { Percent } from 'src/app/models/percent';
import { Product } from 'src/app/models/product';
import { ResponseInfo } from 'src/app/models/responesInfo';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-price-update',
  templateUrl: './price-update.component.html',
  styleUrls: ['./price-update.component.scss']
})
export class PriceUpdateComponent {
  newProductList: Product[] = [];
  priceUpdated: boolean = false;
  percent: number | null = null;
  prefix: string = '';
  totalCount:number;
  first:number = 0;
  rows:number = 15;
  page: number = 0;

  constructor(private logger: NGXLogger,public dialogRef: MatDialogRef<PriceUpdateComponent>,
    @Inject(MAT_DIALOG_DATA) public data:any, private productService: ProductService){

  }

  isFormValid(): boolean {
    return this.prefix.trim() !== '' && this.percent !== null && this.percent >= 0;
  }

  preventNegative(event: KeyboardEvent): void {
    if (event.key === '-' || event.key === 'e') {
      event.preventDefault();
    }
  }

  update(){
    if (this.isFormValid()) {
      const newObj = new Percent;
      newObj.percent = this.percent!
      newObj.prefix = this.prefix
      this.productService.sendPercent(newObj).subscribe((respones: ResponseInfo<Product[]>)=>{
        if(respones.data !== null){
          this.newProductList = respones.data;
          this.priceUpdated = true;
        } else {
          alert(respones.errorMsg)
        }
      });
    }
  }

  close(data: any){
    this.dialogRef.close(data);
  }

}
