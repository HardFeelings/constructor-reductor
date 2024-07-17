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
  frequencyArray: number[]=[50,60];
  rpmArray: number[]=[750, 1000, 1500, 3000];

  @Input() idProductType: number;
  motorTypeId: number;
  power!: number;
  options: number[] = [];
  filter: Filter = new Filter();
  foundProducts: Product[];


  constructor(private motorService: MotorService, private productService: ProductService){
  }

  ngOnInit() {
    this.getAllMotorType();
    this.getByProductTypeOptionId(this.idProductType);
    this.filter.productTypeId = this.idProductType;
  }

  getAllMotorType() {
    this.motorService.getAllMotorType().subscribe(
      (respones: ResponseInfo<EngineType[]>) => {
        console.log("Data getAllMotorType: ", respones.data);
        this.motorType = respones.data;
      },
      (exepcion: any) => {
        console.error("Error getAllMotorType:", exepcion.error);
      }
    );
  }

  getMotorAdapterByMotorTypeId(id:number) {
    this.motorService.getMotorAdapterByMotorTypeId(id).subscribe(
      (respones: ResponseInfo<EngineAdapterType[]>)=>{
        console.log("Data getMotorAdapterByMotorTypeId", respones.data);
        this.engineAdapterTypeByMotorTypeId = respones.data;
      },
      (error:any) =>{
        console.error("Error getMotorAdapterByMotorTypeId:", error);
      }
    );
  }

  getByProductTypeOptionId(id:number) {
    this.productService.getByProductTypeOptionId(id).subscribe(
      (respones: ResponseInfo<ProductOption[]>)=>{
        console.log("Data getByProductTypeOptionId", respones.data);
        this.productOption = respones.data;
      },
      (error:any) =>{
        console.error("Error getByProductTypeOptionId:", error);
      }
    );
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
    }
  }

  frequencySelected(event: Event) {
    const selectedElement = event.target as HTMLSelectElement;
    const selectedValue = selectedElement.value;
    console.log('Выбранное значение frequency:', selectedValue);
    const intselectedValue: number = parseInt(selectedValue, 10);
    console.log('Выбранное значение int frequency:', selectedValue);

    if (intselectedValue) {
      this.filter.frequency = intselectedValue;
    }
  }

  rpmSelected(event: Event) {
    const selectedElement = event.target as HTMLSelectElement;
    const selectedValue = selectedElement.value;
    console.log('Выбранное значение rpm:', selectedValue);
    const intselectedValue: number = parseInt(selectedValue, 10);
    console.log('Выбранное значение int rpm:', selectedValue);

    if (intselectedValue) {
      this.filter.rpm = intselectedValue;
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

  searchProduct(filter: Filter){
    filter.power = this.power;
    console.log('filter', filter);
    this.productService.postFilter(filter).subscribe(
      (respones: ResponseInfo<Product[]>)=>{
        console.log("Data searchProduct", respones.data);
        console.log("respones searchProduct", respones);
        this.foundProducts = respones.data;
      },
      (error:any) =>{
        console.error("Error searchProduct:", error);
      }
    );
  }

  downloadImage(id:number,filename: string){
    this.productService.downloadImageById(id,filename);
  }

}
