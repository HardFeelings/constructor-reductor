import { Component, Input } from '@angular/core';
import { EngineAdapterType, EngineType } from 'src/app/models/engine';
import { Filter } from 'src/app/models/filter';
import { Product, ProductOption } from 'src/app/models/product';
import { ResponseInfo } from 'src/app/models/responesInfo';
import { MotorService } from 'src/app/sevices/motor.service';
import { ProductService } from 'src/app/sevices/product.service';

@Component({
  selector: 'app-engine',
  templateUrl: './engine.component.html',
  styleUrls: ['./engine.component.scss']
})
export class EngineComponent {
  engineAdapterTypeByMotorTypeId: EngineAdapterType[];
  motorType: EngineType[];
  productOption: ProductOption[];
  // frequencyArray: number[]=[50,60];
  // posTerminalBoxArray: number[]=[90,180,270,360];
  rpmArray: number[]=[750, 1000, 1500, 3000];
  @Input() idProductType: number;
  motorTypeId: number | undefined;
  power!: number;
  options: number[] = [];
  filter: Filter = new Filter();
  foundProducts: Product[];
  rpm!:number;


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

  // frequencySelected(event: Event) {
  //   const selectedElement = event.target as HTMLSelectElement;
  //   const selectedValue = selectedElement.value;
  //   console.log('Выбранное значение posTerminal:', selectedValue);
  //   const intselectedValue: number = parseInt(selectedValue, 10);
  //   console.log('Выбранное значение int posTerminal:', selectedValue);

  //   if (intselectedValue) {
  //     this.filter.posTerminalBox = intselectedValue;
  //   }
  //   if(selectedValue == "Select"){
  //     this.filter.posTerminalBox = undefined;
  //      console.log('Другое значение posTerminal:', this.filter.posTerminalBox);
  //   }
  // }

  // rpmSelected(event: Event) {
  //   const selectedElement = event.target as HTMLSelectElement;
  //   const selectedValue = selectedElement.value;
  //   console.log('Выбранное значение rpm:', selectedValue);
  //   const intselectedValue: number = parseInt(selectedValue, 10);
  //   console.log('Выбранное значение int rpm:', selectedValue);

  //   if (intselectedValue) {
  //     this.filter.rpm = intselectedValue;
  //   }
  // }

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

  searchProduct(filter: Filter){
    filter.power = this.power;
    filter.rpm = this.rpm
    console.log('filter', filter);
    this.productService.postFilter(filter).subscribe((respones: ResponseInfo<Product[]>)=>{
      if(respones.data !== null){
        console.log("Data searchProduct", respones.data);
        console.log("respones searchProduct", respones);
        this.foundProducts = respones.data;
      } else {
        alert(JSON.stringify(respones.errorMsg))
      }
    });
  }

  preventNegative(event: KeyboardEvent): void {
    if (event.key === '-' || event.key === 'e') {
      event.preventDefault();
    }
  }


  downloadImage(id:number,filename: string){
    this.productService.downloadImageById(id,filename);
  }

}
