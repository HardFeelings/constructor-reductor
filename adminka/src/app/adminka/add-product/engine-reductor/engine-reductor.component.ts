import { Component, Input, Output, EventEmitter } from '@angular/core';
import { Engine, EngineAdapterType, EngineType } from 'src/app/models/engine';
import { ResponseInfo } from 'src/app/models/responesInfo';
import { Reducer, ReducerAdapterType, ReducerInputType, ReducerInstallationType, ReducerMounting, ReducerOutputShaftType, ReducerSize, ReducerType } from 'src/app/models/reducer';
import { ProductOption,Product } from 'src/app/models/product';
import { ReducerService } from 'src/app/services/reducer.service';
import { ProductService } from 'src/app/services/product.service';
import { MotorService } from 'src/app/services/motor.service';


@Component({
  selector: 'app-engine-reductor',
  templateUrl: './engine-reductor.component.html',
  styleUrls: ['./engine-reductor.component.scss']
})
export class EngineReductorComponent {
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

  reducerType: ReducerType[];
  reducerTypeId: number | undefined;
  resucerSize: ReducerSize[];
  reducerMounting: ReducerMounting[];
  reducerInputType: ReducerInputType[];
  reducerAdapterType: ReducerAdapterType[];
  reducerOutputShaftType: ReducerOutputShaftType[];
  reducerInstallationType: ReducerInstallationType[];
  diamInput: number;
  diamInputAllowance:number;
  diamOutput: number;
  diamOutputAllowance: number;
  ratio: number;
  newReducer!:Reducer;

  constructor(private reducerService: ReducerService, private productService: ProductService, private motorService: MotorService){
    this.newProduct = new Product;
    this.newMotor = new Engine;
    this.newReducer = new Reducer;
  }

  ngOnInit() {
    this.getAllMotorType();
    this.getAllReducerType();
    this.getAllReducerMounting();
    this.getByProductTypeOptionId(this.idProductType);
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

  idMotorTypeSelected(event: Event) {
    const selectedElement = event.target as HTMLSelectElement;
    const selectedValue = selectedElement.value;
    console.log('Выбранное значение MotorType:', selectedValue);
    const selectedMotor = this.motorType.find(type => type.motorTypeName === selectedValue);

    if (selectedMotor) {
      this.motorTypeId = selectedMotor.idMotorType;
      console.log('ID выбранного типа двигателя:', this.motorTypeId);
      if(selectedMotor.idMotorType && selectedMotor.idMotorType !== 1){
        this.getMotorAdapterByMotorTypeId(this.motorTypeId);
      }
    } else {
      console.error('Такой тип двигателя не найден');
       this.motorTypeId = undefined;
       console.log('undefined выбранного типа двигателя:', this.motorTypeId);
    }
  }

  idMotorAdapterTypeSelected(event: Event) {
    const selectedElement = event.target as HTMLSelectElement;
    const selectedValue = selectedElement.value;
    console.log('Выбранное значение AdapterType:', selectedValue);
    const selectedAdapter = this.engineAdapterTypeByMotorTypeId.find(type => type.motorAdapterTypeValue === selectedValue);

    if (selectedAdapter) {
      this.motorAdapterTypeId = selectedAdapter.idMotorAdapterType;
      console.log('ID выбранного фланца двигателя:', selectedAdapter.idMotorAdapterType);
    } else {
      console.error('Такой фланц двигателя не найден');
      this.motorAdapterTypeId = undefined;
       console.log('undefined выбранного фланца двигателя:', this.motorAdapterTypeId);
    }
  }

  frequencySelected(event: Event) {
    const selectedElement = event.target as HTMLSelectElement;
    const selectedValue = selectedElement.value;
    console.log('Выбранное значение frequency:', selectedValue);
    const intselectedValue: number = parseInt(selectedValue, 10);
    console.log('Выбранное значение int frequency:', selectedValue);

    if (intselectedValue) {
      this.newMotor.frequency = intselectedValue;
    }
    if(selectedValue == "Select"){
      this.newMotor.frequency = undefined;
       console.log('undefined значение frequency:', this.newMotor.frequency);
    }
  }

  // cableExitSideSelected(event: Event) {
  //   const selectedElement = event.target as HTMLSelectElement;
  //   const selectedValue = selectedElement.value;
  //   console.log('Выбранное значение  cableExitSide:', selectedValue);
  //   console.log('Выбранное значение int  cableExitSide:', selectedValue);

  //   if (selectedValue) {
  //     this.newMotor.cableExitSide = selectedValue;
  //   }
  //   if(selectedValue == "Select"){
  //     this.newMotor.cableExitSide = undefined;
  //     console.log('undefined значение cableExitSide:',  this.newMotor.cableExitSide);
  //   }
  // }

  // posTerminalSelected(event: Event) {
  //   const selectedElement = event.target as HTMLSelectElement;
  //   const selectedValue = selectedElement.value;
  //   console.log('Выбранное значение posTerminal:', selectedValue);
  //   const intselectedValue: number = parseInt(selectedValue, 10);
  //   console.log('Выбранное значение int posTerminal:', selectedValue);

  //   if (intselectedValue) {
  //     this.newMotor.posTerminalBox = intselectedValue;
  //   }
  //   if(selectedValue == "Select"){
  //     this.newMotor.posTerminalBox = undefined;
  //     console.log('undefined значение posTerminalBox:',  this.newMotor.posTerminalBox);
  //   }
  // }

  getAllReducerType() {
    this.reducerService.getAllReducerTypes().subscribe((respones: ResponseInfo<ReducerType[]>) => {
      if(respones.data !== null){
        console.log("Data getAllReducerType: ", respones.data);
        this.reducerType = respones.data;
      } else {
        alert(JSON.stringify(respones.errorMsg))
      }
    });
  }

  idReducerTypeSelected(event: Event) {
    const selectedElement = event.target as HTMLSelectElement;
    const selectedValue = selectedElement.value;
    console.log('Выбранное значение reducerType:', selectedValue);
    const selectedReducer = this.reducerType.find(type => type.reducerTypeName === selectedValue);

    if (selectedReducer) {
      this.reducerTypeId = selectedReducer.idReducerType;
      this.newReducer.reducerTypeId = selectedReducer.idReducerType;
      console.log('ID выбранного типа редуктора:', this.reducerTypeId);
      this.getReducerSizeByReducerTypeId(this.reducerTypeId);
      this.getReducerInputByReducerTypeId(this.reducerTypeId);
      this.getReducerAdapterByReducerTypeId(this.reducerTypeId);
      this.getReducerOutputShaftTypeByReducerTypeId(this.reducerTypeId);
      this.getReducerInstallationByReducerTypeId(this.reducerTypeId);
    } else {
      console.error('Такой тип редуктора не найден');
      this.reducerTypeId = undefined;
      this.newReducer.reducerTypeId = undefined;
      console.log('undefined id выбранного типа редуктора:', this.reducerTypeId);
    }
  }

  getReducerInputByReducerTypeId(id:number) {
    this.reducerService.getReducerInputByReducerTypeId(id).subscribe((respones: ResponseInfo<ReducerInputType[]>)=>{
      if(respones.data !== null){
        console.log("Data getReducerInputByReducerTypeId", respones.data);
        this.reducerInputType = respones.data;
      } else {
        alert(JSON.stringify(respones.errorMsg))
      }
    });
  }

  idReducerInputSelected(event: Event) {
    const selectedElement = event.target as HTMLSelectElement;
    const selectedValue = selectedElement.value;
    console.log('Выбранное значение ReducerInputType:', selectedValue);
    const selectedInput= this.reducerInputType.find(type => type.reducerInputTypeValue === selectedValue);

    if (selectedInput) {
      this.newReducer.reducerInputTypeId = selectedInput.idReducerInputType;
      console.log('ID выбранного типа входа:', selectedInput.idReducerInputType);
    } else {
      console.error('Такого типа входа не найдено');
      this.newReducer.reducerInputTypeId = undefined;
      console.log('undefined выбранного типа входа:', this.newReducer.reducerInputTypeId);
    }
  }

  getReducerAdapterByReducerTypeId(id:number) {
    this.reducerService.getReducerAdapterByReducerTypeId(id).subscribe((respones: ResponseInfo<ReducerAdapterType[]>)=>{
      if(respones.data !== null){
        console.log("Data getReducerAdapterByReducerTypeId", respones.data);
        this.reducerAdapterType = respones.data;
      } else {
        alert(JSON.stringify(respones.errorMsg))
      }
    });
  }

  // idReducerAdapterSelected(event: Event) {
  //   const selectedElement = event.target as HTMLSelectElement;
  //   const selectedValue = selectedElement.value;
  //   console.log('Выбранное значение ReducerAdapterType:', selectedValue);
  //   const selectedAdapter= this.reducerAdapterType.find(type => type.reducerAdapterTypeValue === selectedValue);

  //   if (selectedAdapter) {
  //     this.newReducer.reducerAdapterTypeId = selectedAdapter.idReducerAdapterType;
  //     console.log('ID выбранного размера адаптера:', selectedAdapter.idReducerAdapterType);
  //   } else {
  //     console.error('Такого размера адаптера не найдено');
  //     this.newReducer.reducerAdapterTypeId = undefined;
  //     console.log('undefined выбранного размера адаптера:',  this.newReducer.reducerAdapterTypeId );
  //   }
  // }

  getReducerOutputShaftTypeByReducerTypeId(id:number) {
    this.reducerService.getReducerOutputShaftTypeByReducerTypeId(id).subscribe((respones: ResponseInfo<ReducerOutputShaftType[]>)=>{
      if(respones.data !== null){
        console.log("Data getReducerOutputShaftTypeByReducerTypeId", respones.data);
        this.reducerOutputShaftType = respones.data;
      } else {
        alert(JSON.stringify(respones.errorMsg))
      }
    });
  }

  idReducerOutputShaftTypeSelected(event: Event) {
    const selectedElement = event.target as HTMLSelectElement;
    const selectedValue = selectedElement.value;
    console.log('Выбранное значение ReducerOutputShaftType:', selectedValue);
    const selectedOutputShaft= this.reducerOutputShaftType.find(type => type.reducerOutputShaftTypeValue === selectedValue);

    if (selectedOutputShaft) {
      this.newReducer.reducerOutputShaftTypeId = selectedOutputShaft.idReducerOutputShaftType;
      console.log('ID выбранноой формы выходного вала:', selectedOutputShaft.idReducerOutputShaftType);
    } else {
      console.error('Такой формы не найдено');
      this.newReducer.reducerOutputShaftTypeId = undefined;
      console.log('undefined выбранноой формы выходного вала:', this.newReducer.reducerOutputShaftTypeId);
    }
  }

  getReducerInstallationByReducerTypeId(id:number) {
    this.reducerService.getReducerInstallationByReducerTypeId(id).subscribe((respones: ResponseInfo<ReducerInstallationType[]>)=>{
      if(respones.data !== null){
        console.log("Data getReducerInstallationByReducerTypeId", respones.data);
        this.reducerInstallationType = respones.data;
      } else {
        alert(JSON.stringify(respones.errorMsg))
      }
    });
  }

  idReducerInstallationSelected(event: Event) {
    const selectedElement = event.target as HTMLSelectElement;
    const selectedValue = selectedElement.value;
    console.log('Выбранное значение ReducerInstallationType:', selectedValue);
    const selectedInstallation= this.reducerInstallationType.find(type => type.reducerInstallationTypeValue === selectedValue);

    if (selectedInstallation) {
      this.newReducer.reducerInstallationTypeId = selectedInstallation.idReducerInstallationType;
      console.log('ID выбранного типа крепления:', selectedInstallation.idReducerInstallationType);
    } else {
      console.error('Такое крепление не найдено');
      this.newReducer.reducerInstallationTypeId = undefined;
      console.log('undefined выбранного типа крепления:', this.newReducer.reducerInstallationTypeId);
    }
  }

  getAllReducerMounting(){
    this.reducerService.getAllReducerMounting().subscribe((respones: ResponseInfo<ReducerMounting[]>) => {
      if(respones.data !== null){
        console.log("Data getAllReducerMounting: ", respones.data);
        this.reducerMounting = respones.data;
      } else {
        alert(JSON.stringify(respones.errorMsg))
      }
    });
  }

  idReducerMountingSelected(event: Event) {
    const selectedElement = event.target as HTMLSelectElement;
    const selectedValue = selectedElement.value;
    console.log('Выбранное значение ReducerMounting:', selectedValue);
    const selectedMounting= this.reducerMounting.find(type => type.reducerMountingValue === selectedValue);

    if (selectedMounting) {
      this.newReducer.reducerMountingId = selectedMounting.idReducerMounting;
      console.log('ID выбранного монтажного положения:', selectedMounting.idReducerMounting);
    } else {
      console.error('Такое положение не найдено');
      this.newReducer.reducerMountingId = undefined;
      console.log('undefined выбранного монтажного положения:', this.newReducer.reducerMountingId);
    }
  }

  getReducerSizeByReducerTypeId(id:number) {
    this.reducerService.getReducerSizeByReducerTypeId(id).subscribe((respones: ResponseInfo<ReducerSize[]>)=>{
      if(respones.data !== null){
        console.log("Data getResucerSizeByMotorTypeId", respones.data);
        this.resucerSize = respones.data;
      } else {
        alert(JSON.stringify(respones.errorMsg))
      }
    });
  }

  idReducerSizeBSelected(event: Event) {
    const selectedElement = event.target as HTMLSelectElement;
    const selectedValue = selectedElement.value;
    console.log('Выбранное значение ReducerSize:', selectedValue);
    const selectedSize = this.resucerSize.find(type => type.reducerSizeValue === selectedValue);

    if (selectedSize) {
      this.newReducer.reducerSizeId = selectedSize.idReducerSize;
      console.log('ID выбранного размера редуктора:', selectedSize.idReducerSize);
    } else {
      console.error('Такой размер не найден');
      this.newReducer.reducerSizeId = undefined;
      console.log('undefined выбранного размера редуктора:', this.newReducer.reducerSizeId);
    }
  }
  // rpmSelected(event: Event) {
  //   const selectedElement = event.target as HTMLSelectElement;
  //   const selectedValue = selectedElement.value;
  //   console.log('Выбранное значение rpm:', selectedValue);
  //   const intselectedValue: number = parseInt(selectedValue, 10);
  //   console.log('Выбранное значение int rpm:', selectedValue);

  //   if (intselectedValue) {
  //     this.newProduct.rpm = intselectedValue;
  //   }
  // }

  onCheckboxChange(event: Event, optionId: number) {
    const target = event.target as HTMLInputElement;
    if (target.checked) {
      this.options.push(optionId);
      this.newProduct.optionsIds = this.options;
      console.log(`Checkbox with id ${optionId} is checked.`);
      console.log(this.newProduct.optionsIds);
    }
    else {
      const index = this.options.indexOf(optionId);
      if (index !== -1) {
        this.options.splice(index, 1);
        this.newProduct.optionsIds = this.options;
      }
      console.log(`Checkbox with id ${optionId} is unchecked.`);
      console.log(this.newProduct.optionsIds);
    }
  }


  getByProductTypeOptionId(id:number) {
    this.productService.getByProductTypeOptionId(id).subscribe((respones: ResponseInfo<ProductOption[]>)=>{
      if(respones.data !== null){
        console.log("Data getByProductTypeOptionId motor-reducer", respones.data);
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

  dynamicAddProduct(){
    this.newProduct.productTypeId = this.idProductType;

    this.newMotor.power = this.power;
    this.newMotor.efficiency = this.efficiency;
    this.newMotor.ratedCurrent = this.ratedCurrent;
    this.newMotor.momentOfInertia = this.momentOfInertia;
    this.newMotor.momentOfInertia = this.momentOfInertia;
    this.newMotor.motorTypeId = this.motorTypeId;
    this.newMotor.motorAdapterTypeId = this.motorAdapterTypeId;
    this.newProduct.motor = this.newMotor;

    this.newReducer.ratio = this.ratio;
    // this.newReducer.diameterInputShaft = this.diamInput;
    this.newReducer.diameterOutputShaft = this.diamOutput;
    this.newProduct.reducer = this.newReducer;

    this.newProduct.rpm = this.rpm;
    this.newProduct.name = this.name;
    this.newProduct.weight = this.weight;
    this.newProduct.torqueMoment = this.torqueMoment;
    this.newProduct.price = this.price;
    this.newProduct.serviceFactor = this.serviceFactor;

    console.log('dynamicProduct', this.newProduct);

    this.dynamicProduct.emit(this.newProduct);

  }

}
