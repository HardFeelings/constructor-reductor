import { Component, Inject } from '@angular/core';
import { ProductService } from '../sevices/product.service';
import { Product, ProductType, enProduct } from '../models/product';
import { ResponseInfo } from '../models/responesInfo';
import { DataService } from '../sevices/data.service';
import { Router } from '@angular/router';
import { CommercialService } from '../sevices/commercial.service';
import { CommercialProp } from '../models/commercialProp';
import { ManagerService } from '../sevices/manager.service';
import { Manager } from '../models/manager';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { CommercialPropItem } from '../models/commercialPropItem';
import { PaymentTerms } from '../models/paymentTerm';
import { CommercialPropTerm } from '../models/commercialPropTerm';


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
  payment_list: PaymentTerms[];
  ordNow: number = 1;
  selectedButton: number | null = null;

  constructor(private productService: ProductService,
    private commercialService: CommercialService,
    private managerService: ManagerService,
    public dialogRef: MatDialogRef<SearchPageComponent>,
    @Inject(MAT_DIALOG_DATA) public data:number) {
      this.idCommercialProp = data;
      console.log('this.idCommercialProp',this.idCommercialProp);
      this.commercialProp = new CommercialProp;
      this.commercialProp.commercialPropItems = [];
  }

  ngOnInit() {
    this.getAllManagers();
    this.getAllPaymentTerms();
    if(this.idCommercialProp !== null){
      this.getCommercialPropById(this.idCommercialProp);
    }
    else{
      console.log('this.idCommercialProp == null',this.idCommercialProp);
    }
    if (!this.commercialProp.manager) {
      this.commercialProp.manager = null;
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
    // this.commercialProp.timestamp = null;
    this.commercialService.saveCommercialProp(this.commercialProp).subscribe((respones: ResponseInfo<CommercialProp>)=>{
      console.log('SaveData', this.commercialProp );
      if(respones.data !== null){
        console.log('ReturnSaveData', respones.data);
        this.dialogRef.close(respones.data);
      }
      else{
        alert(JSON.stringify(respones.errorMsg))
      }
    });
  }

  downloadImage(id:number,filename: string){
    this.productService.downloadImageById(id,filename);
  }

  close(): void {
    this.callSearch = false;
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

  getAllPaymentTerms(){
    this.commercialService.getPaymentTerms().subscribe((respones: ResponseInfo<PaymentTerms[]>) => {
      if(respones.data !== null){
        console.log("Data getAllPaymentTerms: ", respones.data);
        this.payment_list = respones.data;
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


  selectedPayment(event: Event, commercialTerm: CommercialPropTerm) {
    const selectedVisibleName = (event.target as HTMLSelectElement).value;
    const selectedPayment = this.payment_list.find(payment => payment.visibleName === selectedVisibleName);

    if (selectedPayment) {
      console.log(selectedPayment);
      commercialTerm.paymentTerms = selectedPayment;
    } else {
      console.log('Payment не найден');
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

  childSelectedProduct(event: Product) {
    this.callSearch = false;
    const existingItem = this.commercialProp.commercialPropItems.find(i => i.product.idProduct === event.idProduct);
    if (existingItem) {
        existingItem.amount += 1;
    } else {
        let newPropItem = new CommercialPropItem();
        newPropItem.product = event;
        newPropItem.amount = 1;
        newPropItem.commercialPropId = this.commercialProp.idCommercialProp !== null ? this.commercialProp.idCommercialProp : null;
        this.commercialProp.commercialPropItems.push(newPropItem);
    }
  }

  addTerm() {
    let newPropTerm = new CommercialPropTerm();
    this.ordNow = this.commercialProp.commercialPropTerms.reduce((max, propTerm) => {
      return propTerm.ord > max ? propTerm.ord : max;
    }, 0);
    newPropTerm.ord = this.ordNow + 1;
    this.commercialProp.commercialPropTerms.push(newPropTerm);
}

  refreshOrd(ord: number) {
    this.commercialProp.commercialPropTerms.forEach(propTerm => {
      if (propTerm.ord >= ord) {
        propTerm.ord -= 1;
      }
    });
    this.ordNow -= 1;
  }

  deleteTerm(ord: number) {
    this.commercialProp.commercialPropTerms = this.commercialProp.commercialPropTerms.filter(propTerm => propTerm.ord !== ord);
    this.refreshOrd(ord);
  }

  deleteSelectProduct(id: number){
    const oldItem = this.commercialProp.commercialPropItems.filter(i => i.idCommercialPropItem !== id);
    console.log('Удаляем продукт с id:', id);
    console.log('Старые элементы:', this.commercialProp.commercialPropItems);
    console.log('Новые элементы:', oldItem);
    this.commercialProp.commercialPropItems = oldItem;
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


