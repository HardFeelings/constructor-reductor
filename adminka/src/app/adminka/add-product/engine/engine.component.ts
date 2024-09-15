import { Component, Input, Output, EventEmitter} from '@angular/core';
import { Engine, EngineAdapterType, EngineType } from 'src/app/models/engine';
import { Product, ProductOption } from 'src/app/models/product';
import { ResponseInfo } from 'src/app/models/responesInfo';
import { MotorService } from 'src/app/services/motor.service';
import { ProductService } from 'src/app/services/product.service';
import { NGXLogger } from "ngx-logger";

@Component({
  selector: 'app-engine',
  templateUrl: './engine.component.html',
  styleUrls: ['./engine.component.scss']
})
export class EngineComponent {
  engineAdapterTypeByMotorTypeId: EngineAdapterType[];
  motorType: EngineType[];
  productOption: ProductOption[];
  frequencyArray: number[]=[50,60, 100];
  posTerminalBoxArray: number[]=[90,180,270,360];
  rpmArray: number[]=[750, 1000, 1500, 3000];
  @Input() idProductType: number;
  @Output() dynamicProduct = new EventEmitter<Product>();
  motorTypeId: number | undefined;
  motorAdapterTypeId: number | undefined;
  power!: number;
  efficiency!:number;
  ratedCurrent!:number;
  momentOfInertia!:number;
  name!: string;
  weight!: number;
  price!:number;
  rpm!:number;
  torqueMoment!:number;
  serviceFactor!:number;
  cableExitSide:string []=["X","1","2","3"];
  options: number[] = [];
  newProduct!:Product;
  newMotor!:Engine;

  constructor(private logger: NGXLogger,private motorService: MotorService, private productService: ProductService){
    this.newProduct = new Product;
    this.newMotor = new Engine;
  }

  ngOnInit() {
    this.getAllMotorType();
    this.getByProductTypeOptionId(this.idProductType);
  }

  getAllMotorType() {
    this.motorService.getAllMotorType().subscribe((respones: ResponseInfo<EngineType[]>) => {
      if(respones.data !== null){
        this.logger.log("Data getAllMotorType: ", respones.data);
        this.motorType = respones.data;
      } else {
        alert(JSON.stringify(respones.errorMsg))
      }
    });
  }

  getMotorAdapterByMotorTypeId(id:number) {
    this.motorService.getMotorAdapterByMotorTypeId(id).subscribe((respones: ResponseInfo<EngineAdapterType[]>)=>{
      if(respones.data !== null){
        this.logger.log("Data getMotorAdapterByMotorTypeId", respones.data);
        this.engineAdapterTypeByMotorTypeId = respones.data;
      } else {
        alert(JSON.stringify(respones.errorMsg))
      }
    });
  }

  idMotorTypeSelected(event: Event) {
    const selectedElement = event.target as HTMLSelectElement;
    const selectedValue = selectedElement.value;
    this.logger.log('Выбранное значение MotorType:', selectedValue);
    const selectedMotor = this.motorType.find(type => type.motorTypeName === selectedValue);

    if (selectedMotor) {
      this.motorTypeId = selectedMotor.idMotorType;
      this.logger.log('ID выбранного типа двигателя:', this.motorTypeId);
      if(selectedMotor.idMotorType && selectedMotor.idMotorType !== 1){
        this.getMotorAdapterByMotorTypeId(this.motorTypeId);
      }
    } else {
      console.error('Такой тип двигателя не найден');
       this.motorTypeId = undefined;
       this.logger.log('undefined выбранного типа двигателя:', this.motorTypeId);
    }
  }

  idMotorAdapterTypeSelected(event: Event) {
    const selectedElement = event.target as HTMLSelectElement;
    const selectedValue = selectedElement.value;
    this.logger.log('Выбранное значение AdapterType:', selectedValue);
    const selectedAdapter = this.engineAdapterTypeByMotorTypeId.find(type => type.motorAdapterTypeValue === selectedValue);

    if (selectedAdapter) {
      this.motorAdapterTypeId = selectedAdapter.idMotorAdapterType;
      this.logger.log('ID выбранного фланца двигателя:', selectedAdapter.idMotorAdapterType);
    } else {
      console.error('Такой фланц двигателя не найден');
      this.motorAdapterTypeId = undefined;
       this.logger.log('undefined выбранного фланца двигателя:', this.motorAdapterTypeId);
    }
  }

  frequencySelected(event: Event) {
    const selectedElement = event.target as HTMLSelectElement;
    const selectedValue = selectedElement.value;
    this.logger.log('Выбранное значение frequency:', selectedValue);
    const intselectedValue: number = parseInt(selectedValue, 10);
    this.logger.log('Выбранное значение int frequency:', selectedValue);

    if (intselectedValue) {
      this.newMotor.frequency = intselectedValue;
    }
    if(selectedValue == "Select"){
      this.newMotor.frequency = undefined;
       this.logger.log('undefined значение frequency:', this.newMotor.frequency);
    }
  }

  // cableExitSideSelected(event: Event) {
  //   const selectedElement = event.target as HTMLSelectElement;
  //   const selectedValue = selectedElement.value;
  //   this.logger.log('Выбранное значение  cableExitSide:', selectedValue);
  //   this.logger.log('Выбранное значение int  cableExitSide:', selectedValue);

  //   if (selectedValue) {
  //     this.newMotor.cableExitSide = selectedValue;
  //   }
  //   if(selectedValue == "Select"){
  //     this.newMotor.cableExitSide = undefined;
  //     this.logger.log('undefined значение cableExitSide:',  this.newMotor.cableExitSide);
  //   }
  // }

  // posTerminalSelected(event: Event) {
  //   const selectedElement = event.target as HTMLSelectElement;
  //   const selectedValue = selectedElement.value;
  //   this.logger.log('Выбранное значение posTerminal:', selectedValue);
  //   const intselectedValue: number = parseInt(selectedValue, 10);
  //   this.logger.log('Выбранное значение int posTerminal:', selectedValue);

  //   if (intselectedValue) {
  //     this.newMotor.posTerminalBox = intselectedValue;
  //   }
  //   if(selectedValue == "Select"){
  //     this.newMotor.posTerminalBox = undefined;
  //     this.logger.log('undefined значение posTerminalBox:',  this.newMotor.posTerminalBox);
  //   }
  // }

  // rpmSelected(event: Event) {
  //   const selectedElement = event.target as HTMLSelectElement;
  //   const selectedValue = selectedElement.value;
  //   this.logger.log('Выбранное значение rpm:', selectedValue);
  //   const intselectedValue: number = parseInt(selectedValue, 10);
  //   this.logger.log('Выбранное значение int rpm:', selectedValue);

  //   if (intselectedValue) {
  //     this.newProduct.rpm = intselectedValue;
  //   }
  // }

  onCheckboxChange(event: Event, optionId: number) {
    const target = event.target as HTMLInputElement;
    if (target.checked) {
      this.options.push(optionId);
      this.newProduct.optionsIds = this.options;
      this.logger.log(`Checkbox with id ${optionId} is checked.`);
      this.logger.log(this.newProduct.optionsIds);
    }
    else {
      const index = this.options.indexOf(optionId);
      if (index !== -1) {
        this.options.splice(index, 1);
        this.newProduct.optionsIds = this.options;
      }
      this.logger.log(`Checkbox with id ${optionId} is unchecked.`);
      this.logger.log(this.newProduct.optionsIds);
    }
  }

  preventNegative(event: KeyboardEvent): void {
    if (event.key === '-' || event.key === 'e') {
      event.preventDefault();
    }
  }

  dynamicAddProduct(){
    this.newMotor.power = this.power;
    this.newMotor.efficiency = this.efficiency;
    this.newMotor.ratedCurrent = this.ratedCurrent;
    this.newMotor.momentOfInertia = this.momentOfInertia;
    this.newMotor.momentOfInertia = this.momentOfInertia;
    this.newMotor.motorTypeId = this.motorTypeId;
    this.newMotor.motorAdapterTypeId = this.motorAdapterTypeId;

    this.newProduct.rpm = this.rpm;
    this.newProduct.productTypeId = this.idProductType;
    this.newProduct.motor = this.newMotor;
    this.newProduct.name = this.name;
    this.newProduct.weight = this.weight;
    this.newProduct.torqueMoment = this.torqueMoment;
    this.newProduct.price = this.price;
    this.newProduct.serviceFactor = this.serviceFactor;

    this.logger.log('dynamicProduct', this.newProduct);

    this.dynamicProduct.emit(this.newProduct);

  }

  getByProductTypeOptionId(id:number) {
    this.productService.getByProductTypeOptionId(id).subscribe((respones: ResponseInfo<ProductOption[]>)=>{
      if(respones.data !== null){
        this.logger.log("Data getByProductTypeOptionId", respones.data);
        this.productOption = respones.data;
      } else {
        alert(JSON.stringify(respones.errorMsg))
      }
    });
  }

  onFileSelected(event: any) {
    const selectedFile = event.target.files[0];
    if (selectedFile) {
      const reader = new FileReader();
      reader.onload = (e: any) => {
        const base64Image = e.target.result;
        const base64WithoutPrefix = base64Image.split(',')[1];
        this.logger.log('base64:', base64WithoutPrefix);
        setTimeout(() => {
          this.newProduct.imageString = base64WithoutPrefix;
          this.newProduct.imageEmpty = false;
          this.newProduct.imageChanged = true;
        }, 0);
      };
      reader.readAsDataURL(selectedFile);
    }
  }

}
