import { Component, Input, Output, EventEmitter } from '@angular/core';
import { EngineAdapterType, EngineType } from 'src/app/models/engine';
import { Filter } from 'src/app/models/filter';
import { Page } from 'src/app/models/page';
import { Product, ProductOption } from 'src/app/models/product';
import { ResponseInfo } from 'src/app/models/responesInfo';
import { MotorService } from 'src/app/services/motor.service';
import { ProductService } from 'src/app/services/product.service';


@Component({
  selector: 'app-enginecomm',
  templateUrl: './enginecomm.component.html',
  styleUrls: ['./enginecomm.component.scss']
})
export class EngineCommComponent {
  engineAdapterTypeByMotorTypeId: EngineAdapterType[];
  motorType: EngineType[];
  productOption: ProductOption[];
  rpmArray: number[]=[750, 1000, 1500, 3000];
  @Input() idProductType: number;
  motorTypeId: number | undefined;
  power!: number;
  options: number[] = [];
  filter: Filter = new Filter();
  foundProducts: Product[];
  rpm!:number;

  totalCount: number;
  newFilter: Filter;

  @Output() selectedProduct = new EventEmitter<Product>();


  constructor(private motorService: MotorService, private productService: ProductService){
  }

  ngOnInit() {
    this.getAllMotorType();
    this.getByProductTypeOptionId(this.idProductType);
    this.filter.productTypeId = this.idProductType;
  }

  getAllMotorType() {
    this.motorService.getAllMotorType().subscribe((respones: ResponseInfo<EngineType[]>) => {
      if(respones.data !== null){
        console.log("Data getAllMotorType: ", respones.data);
        this.motorType = respones.data;
      } else {
        alert(JSON.stringify(respones.errorMsg))
      }
    });
  }

  getMotorAdapterByMotorTypeId(id:number) {
    this.motorService.getMotorAdapterByMotorTypeId(id).subscribe((respones: ResponseInfo<EngineAdapterType[]>)=>{
      if(respones.data !== null){
        console.log("Data getMotorAdapterByMotorTypeId", respones.data);
        this.engineAdapterTypeByMotorTypeId = respones.data;
      } else {
        alert(JSON.stringify(respones.errorMsg))
      }
    });
  }

  getByProductTypeOptionId(id:number) {
    this.productService.getByProductTypeOptionId(id).subscribe((respones: ResponseInfo<ProductOption[]>)=>{
      if(respones.data !== null){
        console.log("Data getByProductTypeOptionId", respones.data);
        this.productOption = respones.data;
      } else {
        alert(JSON.stringify(respones.errorMsg))
      }
    });
  }

  idMotorTypeSelected(event: Event) {
    const selectedElement = event.target as HTMLSelectElement;
    const selectedValue = selectedElement.value;
    console.log('Выбранное значение MotorType:', selectedValue);
    const selectedMotor = this.motorType.find(type => type.motorTypeName === selectedValue);

    if (selectedMotor) {
      this.motorTypeId = selectedMotor.idMotorType;
      this.filter.motorTypeId = selectedMotor.idMotorType;
      console.log('ID выбранного типа двигателя:', this.motorTypeId);
      if(selectedMotor.idMotorType && selectedMotor.idMotorType !== 1){
        this.getMotorAdapterByMotorTypeId(this.motorTypeId);
      }
    } else {
      console.error('Такой тип двигателя не найден');
      this.motorTypeId = undefined;
      this.filter.motorTypeId = undefined;
      console.log('undefined выбранного типа двигателя:', this.filter.motorTypeId);
    }
  }

  idMotorAdapterTypeSelected(event: Event) {
    const selectedElement = event.target as HTMLSelectElement;
    const selectedValue = selectedElement.value;
    console.log('Выбранное значение AdapterType:', selectedValue);
    const selectedAdapter = this.engineAdapterTypeByMotorTypeId.find(type => type.motorAdapterTypeValue === selectedValue);

    if (selectedAdapter) {
      this.filter.motorAdapterTypeId = selectedAdapter.idMotorAdapterType;
      console.log('ID выбранного фланца двигателя:', selectedAdapter.idMotorAdapterType);
    } else {
      console.error('Такой фланц двигателя не найден');
      this.filter.motorAdapterTypeId = undefined;
      console.log('undefined выбранного фланца двигателя:',  this.filter.motorAdapterTypeId);
    }
  }

  onCheckboxChange(event: Event, optionId: number) {
    const target = event.target as HTMLInputElement;
    if (target.checked) {
      this.options.push(optionId);
      this.filter.productOptions = this.options;
      console.log(`Checkbox with id ${optionId} is checked.`);
      console.log(this.filter.productOptions);
    }
    else {
      const index = this.options.indexOf(optionId);
      if (index !== -1) {
        this.options.splice(index, 1);
        this.filter.productOptions = this.options;
      }
      console.log(`Checkbox with id ${optionId} is unchecked.`);
      console.log(this.filter.productOptions);
    }
  }

  preventNegative(event: KeyboardEvent): void {
    if (event.key === '-' || event.key === 'e') {
      event.preventDefault();
    }
  }


  // searchProduct(filter: Filter){
  //   filter.power = this.power;
  //   filter.rpm = this.rpm;
  //   console.log('filter', filter);
  //   this.productService.postFilter(filter).subscribe((respones: ResponseInfo<Product[]>)=>{
  //     if(respones.data !== null){
  //       console.log("Data searchProduct", respones.data);
  //       console.log("respones searchProduct", respones);
  //       this.foundProducts = respones.data;
  //     } else {
  //       alert(JSON.stringify(respones.errorMsg))
  //     }
  //   });
  // }

  onPageChange(event: any){
    console.log("event.page", event.page);
    this.searchProduct(this.newFilter,event.page);
  }

  searchProduct(filter: Filter, page: number){
    filter.power = this.power;
    filter.rpm = this.rpm
    console.log('filter', filter);
    this.newFilter = filter;
    this.productService.postPageFilter(filter, page).subscribe((respones: ResponseInfo<Page<Product>>)=>{
      if(respones.data !== null){
        console.log("Data searchProduct", respones.data.content);
        console.log("respones searchProduct", respones);
        // this.foundProducts = respones.data;
        this.totalCount = respones.data.totalCount;
        this.foundProducts = respones.data.content;
        console.log(" totalCount", respones.data.totalCount);
      } else {
        alert(JSON.stringify(respones.errorMsg))
      }
    });
  }

  downloadImage(id:number,filename: string){
    this.productService.downloadImageById(id,filename);
  }

  selectProduct(product: Product){
    this.selectedProduct.emit(product);

  }

}
