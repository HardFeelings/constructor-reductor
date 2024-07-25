import { Component, Input, Output, EventEmitter} from '@angular/core';
import { Engine, EngineAdapterType, EngineType } from 'src/app/models/engine';
import { Filter } from 'src/app/models/filter';
import { Product, ProductOption } from 'src/app/models/product';
import { ResponseInfo } from 'src/app/models/responesInfo';
import { MotorService } from 'src/app/services/motor.service';
import { ProductService } from 'src/app/services/product.service';

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
  motorTypeId: number;
  motorAdapterTypeId: number;
  power!: number;
  efficiency!:number;
  ratedCurrent!:number;
  momentOfInertia!:number;
  name!: string;
  weight!: number;
  price!:number;
  rpm!:number;
  torqueMoment!:number;
  options: number[] = [];
  // filter: Filter = new Filter();
  // foundProducts: Product[];
  newProduct!:Product;
  newMotor!:Engine;

  constructor(private motorService: MotorService, private productService: ProductService){
    this.newProduct = new Product;
    this.newMotor = new Engine;
  }

  ngOnInit() {
    this.getAllMotorType();
    this.getByProductTypeOptionId(this.idProductType);
    // this.filter.productTypeId = this.idProductType;
  }

  onFileSelected(event: any) {
    const selectedFile = event.target.files[0];

    if (selectedFile) {
      const reader = new FileReader();

      reader.onload = (e: any) => {
        const base64Image = e.target.result;
        const base64WithoutPrefix = base64Image.split(',')[1];
        console.log('base64:', base64WithoutPrefix);

        setTimeout(() => {
          this.newProduct.imageString = base64WithoutPrefix;
          this.newProduct.imageEmpty = false;
          this.newProduct.imageChanged = true;
        }, 0);
      };

      reader.readAsDataURL(selectedFile);
    }
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
      // this.filter.motorTypeId = selectedMotor.idMotorType;
      // this.newMotor.motorTypeId.idMotorType = selectedMotor.idMotorType;
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
      // this.filter.motorAdapterTypeId = selectedAdapter.idMotorAdapterType;
      this.motorAdapterTypeId = selectedAdapter.idMotorAdapterType;
      // this.newMotor.motorAdapterTypeId.idMotorAdapterType = selectedAdapter.idMotorAdapterType;
      console.log('ID выбранного фланца двигателя:', selectedAdapter.idMotorAdapterType);
    } else {
      console.error('Такой фланц двигателя не найден');
    }
  }

  // frequencySelected(event: Event) {
  //   const selectedElement = event.target as HTMLSelectElement;
  //   const selectedValue = selectedElement.value;
  //   console.log('Выбранное значение frequency:', selectedValue);
  //   const intselectedValue: number = parseInt(selectedValue, 10);
  //   console.log('Выбранное значение int frequency:', selectedValue);

  //   if (intselectedValue) {
  //     this.filter.frequency = intselectedValue;
  //   }
  // }


  frequencySelected(event: Event) {
    const selectedElement = event.target as HTMLSelectElement;
    const selectedValue = selectedElement.value;
    console.log('Выбранное значение frequency:', selectedValue);
    const intselectedValue: number = parseInt(selectedValue, 10);
    console.log('Выбранное значение int frequency:', selectedValue);

    if (intselectedValue) {
      // this.filter.posTerminalBox = intselectedValue;
      this.newMotor.frequency = intselectedValue;
    }
  }

  posTerminalSelected(event: Event) {
    const selectedElement = event.target as HTMLSelectElement;
    const selectedValue = selectedElement.value;
    console.log('Выбранное значение frequency:', selectedValue);
    const intselectedValue: number = parseInt(selectedValue, 10);
    console.log('Выбранное значение int frequency:', selectedValue);

    if (intselectedValue) {
      // this.filter.posTerminalBox = intselectedValue;
      this.newMotor.posTerminalBox = intselectedValue;
    }
  }

  rpmSelected(event: Event) {
    const selectedElement = event.target as HTMLSelectElement;
    const selectedValue = selectedElement.value;
    console.log('Выбранное значение rpm:', selectedValue);
    const intselectedValue: number = parseInt(selectedValue, 10);
    console.log('Выбранное значение int rpm:', selectedValue);

    if (intselectedValue) {
      // this.filter.rpm = intselectedValue;
      this.newProduct.rpm = intselectedValue;
    }
  }

  onCheckboxChange(event: Event, optionId: number) {
    const target = event.target as HTMLInputElement;
    if (target.checked) {
      this.options.push(optionId);
      // this.filter.productOptions = this.options;
      this.newProduct.optionsIds = this.options;
      console.log(`Checkbox with id ${optionId} is checked.`);
      // console.log(this.filter.productOptions);
    }
    else {
      const index = this.options.indexOf(optionId);
      if (index !== -1) {
        this.options.splice(index, 1);
        // this.filter.productOptions = this.options;
        this.newProduct.optionsIds = this.options;
      }
      console.log(`Checkbox with id ${optionId} is unchecked.`);
      // console.log(this.filter.productOptions);
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

    this.newProduct.productTypeId = this.idProductType;
    this.newProduct.motor = this.newMotor;
    // this.newProduct.motor.motorTypeId.idMotorType = this.motorTypeId;
    // this.newProduct.motor.motorAdapterTypeId.idMotorAdapterType = this.motorAdapterTypeId;
    this.newProduct.name = this.name;
    this.newProduct.weight = this.weight;
    this.newProduct.torqueMoment = this.torqueMoment;
    this.newProduct.price = this.price;

    console.log('dynamicProduct', this.newProduct);

    this.dynamicProduct.emit(this.newProduct);

  }

  // searchProduct(filter: Filter){
  //   filter.power = this.power;
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

  // downloadImage(id:number,filename: string){
  //   this.productService.downloadImageById(id,filename);
  // }

}
