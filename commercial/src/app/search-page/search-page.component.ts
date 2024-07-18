import { Component, Inject } from '@angular/core';
import { ProductService } from '../sevices/product.service';
import { ProductType, enProduct } from '../models/product';
import { ResponseInfo } from '../models/responesInfo';
import { DataService } from '../sevices/data.service';
import { Router } from '@angular/router';
import { CommercialService } from '../sevices/commercial.service';
import { CommercialProp } from '../models/commercialProp';
import { ManagerService } from '../sevices/manager.service';
import { Manager } from '../models/manager';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';


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

  idCommercialProp:number | null;
  commercialProp: CommercialProp;
  callSearch: boolean = false;
  managers_list: Manager[];

  constructor(private productService: ProductService,
    private dataService: DataService,
    private  router: Router,
    private commercialService: CommercialService,
    private managerService: ManagerService,
    public dialogRef: MatDialogRef<SearchPageComponent>,
    @Inject(MAT_DIALOG_DATA) public data:number) {
      this.idCommercialProp = data;
      console.log('this.idCommercialProp',this.idCommercialProp);
      this.commercialProp = new CommercialProp;
  }


  ngOnInit() {
    // this.idCommercialProp = this.dataService.getId();
    // console.log('this.idCommercialProp',this.idCommercialProp);
    this.getAllManagers();
    if(this.idCommercialProp !== null){
      this.getCommercialPropById(this.idCommercialProp);
    }
    else{
      console.log('this.idCommercialProp == null',this.idCommercialProp);
    }
  }

  getCommercialPropById(id:number){
    this.commercialService.getCommercialPropById(id).subscribe((respones: ResponseInfo<CommercialProp>)=>{
      if(respones.data !== null){
        console.log("Data getCommercialPropById", respones.data);
        this.commercialProp = respones.data;
      } else {
        alert(JSON.stringify(respones.errorMsg))
      }
    });
  }

  goToBackCommercialPageCansel(): void {
    this.dialogRef.close();
  }

  goToBackCommercialPageOk(){
    // this.router.navigate(['']);
  }

  getAllManagers(){
    this.managerService.getAllManagers().subscribe((respones: ResponseInfo<Manager[]>) => {
      if(respones.data !== null){
        console.log("Data getAllNamagers: ", respones.data);
        this.managers_list = respones.data;
      } else {
        alert(JSON.stringify(respones.errorMsg))
      }
    });
  }

  idManagerSelected(event: Event) {
    const selectedElement = event.target as HTMLSelectElement;
    const selectedValue = selectedElement.value;
    console.log('Выбранное значение ManagerSelected:', selectedValue);
    const selectedManager = this.managers_list.find(type => type.shortName === selectedValue);

    if (selectedManager) {
      this.commercialProp.manager = selectedManager;
      console.log('Выбранный менеджер:', selectedManager);
    } else {
      console.error('Такой менеджер не найден');
    }
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
    });
  }

  openSearch(){
    this.getAllProductTypes();
    this.callSearch = true;
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


