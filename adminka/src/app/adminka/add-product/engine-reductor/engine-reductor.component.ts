import { Component, Input, Output, EventEmitter } from '@angular/core';
import { Engine, EngineAdapterType, EngineType } from 'src/app/models/engine';
import { ResponseInfo } from 'src/app/models/responesInfo';
import { Reducer, ReducerAdapterType, ReducerInputType, ReducerInstallationType, ReducerMounting, ReducerOutputShaftType, ReducerSize, ReducerType } from 'src/app/models/reducer';
import { ProductOption,Product } from 'src/app/models/product';
import { ReducerService } from 'src/app/services/reducer.service';
import { ProductService } from 'src/app/services/product.service';
import { MotorService } from 'src/app/services/motor.service';
import { NGXLogger } from "ngx-logger";
import { Filter } from 'src/app/models/filter';
import { Page } from 'src/app/models/page';

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
  polesNumberArray: number[] = [2,4,6,8,10];
  @Input() idProductType: number;
  @Output() dynamicProduct = new EventEmitter<Product>();
  motorTypeId: number | undefined;
  motorAdapterTypeId: number | undefined;
  power!: number;
  efficiency!:number;
  ratedCurrent!:number;
  momentOfInertia!:number;
  name!: string;
  weight: number = 0;
  price:number = 0;
  weightMotors: number = 0;
  priceMotors:number = 0;
  weightReducers: number = 0;
  priceReducers:number = 0;
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
  foundProductsMotors: Product[];
  foundProductsReducers: Product[];
  filterMotors: Filter = new Filter();
  filterReducers: Filter = new Filter();
  selectedPoles: number | undefined;
  newFilterMotors: Filter;
  newFilterReductor: Filter;
  rowsPageMotors: number = 15;
  firstPageMotors: number = 0;
  totalCountMotors:number;
  rowsPageReductors: number = 15;
  firstPageReductors: number = 0;
  totalCountReductors:number;
  selectedMotorId: number | null | undefined;
  selectedReducerId: number | null | undefined;
  selectMot: boolean = false;
  selectRed: boolean = false;

  reducerInstallId: number | undefined;
  reducerInputId: number | undefined;
  reducerOutShaftId: number | undefined;
  reducerMountingId: number| undefined
  reducerSizeId: number | undefined;

  constructor(private logger: NGXLogger,private reducerService: ReducerService, private productService: ProductService, private motorService: MotorService){
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

//   searchMotors(filter: Filter, page: number){
//     if( this.power == 0){
//       filter.power = undefined;
//     }
//     else {
//       filter.power = this.power;
//     }

//     // filter.rpm = this.rpm
//     filter.polesNumber = this.selectedPoles;
//     filter.productTypeId = 1;
//     filter.motorTypeId = this.motorTypeId;
//     filter.motorAdapterTypeId = this.motorAdapterTypeId
//     this.logger.log('filter', filter);
//     this.newFilterMotors = filter;
//     this.productService.postPageFilter(filter, page).subscribe((respones: ResponseInfo<Page<Product>>)=>{
//       if (respones.data !== null && respones.data.content !== null) {

//         const filteredProducts = respones.data.content.filter(product => product.motor !== null);
//         const filteredProducts2 = respones.data.content.filter(product => product.reducer == null);
//         if (filteredProducts.length > 0 && filteredProducts2.length > 0) {
//           this.foundProductsMotors = respones.data.content.filter(product =>
//               product.motor !== null && product.reducer == null
//           );

//           this.logger.log("Data searchReducers", this.foundProductsMotors);
//           this.logger.log("respones searchReducers", respones);
//           this.totalCountMotors = respones.data.totalCount;
//       } else {
//           alert("Моторы не найдены");
//             this.foundProductsMotors = [];
//         }
//     } else {
//         this.logger.log("Response data is null");
//         alert("Список пуст");
//         this.foundProductsMotors = [];
//     }
// });
//   }

searchMotors(filter2: Filter, page: number){
  let filter = new Filter();
  if( this.power == 0){
    filter.power = undefined;
  }
  else {
    filter.power = this.power;
  }

  // filter.rpm = this.rpm
  filter.polesNumber = this.selectedPoles;
  filter.productTypeId = 1;
  filter.motorTypeId = this.motorTypeId;
  filter.motorAdapterTypeId = this.motorAdapterTypeId
  this.logger.log('filter', filter);
  this.newFilterMotors = filter;
  this.productService.postPageFilter(filter, page).subscribe((respones: ResponseInfo<Page<Product>>)=>{
    if(respones.data !== null){
      this.logger.log("Data searchProduct", respones.data.content);
      this.logger.log("respones searchProduct", respones);
      this.totalCountMotors = respones.data.totalCount;
      this.foundProductsMotors = respones.data.content;
      this.logger.log(" totalCount", respones.data.totalCount);
    } else {
      alert(JSON.stringify(respones.errorMsg))
    }
  });
}


  onPageChangeMotors(event: any){
    this.logger.log("event.page", event.page);
    this.searchMotors(this.newFilterMotors,event.page);
  }


  selectMotors(product: Product){
    this.selectedMotorId = product.motor?.idMotor;
    this.selectMot = true;
    this.totalCountMotors = 1;
    this.foundProductsMotors = [];
    this.foundProductsMotors.push(product);
    this.priceMotors = product.price;
    this.weightMotors = product.weight;
    this.price = this.priceMotors + this.priceReducers;
    this.weight = this.weightMotors + this.weightReducers;
  }

  deleteSelectMotors(product: Product){
    this.selectedMotorId = null;
    this.selectMot = false;
    this.totalCountMotors = 0;
    this.foundProductsMotors = [];
    this.priceMotors = 0;
    this.weightMotors = 0;
    this.price = this.priceMotors + this.priceReducers;
    this.weight = this.weightMotors + this.weightReducers;

  }


  // searchReducers(filter: Filter, page: number){
  //   if (this.diamOutput == 0){
  //     filter.diamOutput == undefined;
  //   }
  //   else{
  //     filter.diamOutput = this.diamOutput;
  //   }
  //   filter.diamOutputAllowance = 0;
  //   if (this.ratio == 0){
  //     filter.ratio = undefined;
  //   }
  //   else {
  //     filter.ratio = this.ratio;
  //   }

  //   filter.idReducerType = this.reducerTypeId
  //   filter.idReducerSize = this.reducerSizeId;
  //   filter.idReducerInputType = this.reducerInputId;
  //   filter.idReducerMounting = this.reducerMountingId;
  //   filter.idReducerOutputShaftType = this.reducerOutShaftId;
  //   filter.idReducerInstallationType = this.reducerInstallId;
  //   filter.productTypeId = 2;
  //   this.logger.log('filter', filter);
  //   this.newFilterReductor = filter;
  //   this.productService.postPageFilter(filter, page).subscribe((respones: ResponseInfo<Page<Product>>) => {
  //     if (respones.data !== null && respones.data.content !== null) {

  //         const filteredProducts = respones.data.content.filter(product => product.reducer !== null);
  //         const filteredProducts2 = respones.data.content.filter(product => product.motor == null);

  //         // if (filteredProducts.length > 0 && filteredProducts2.length > 0) {
  //         //     this.logger.log("Data searchReducers", filteredProducts);
  //         //     this.logger.log("respones searchReducers", respones);
  //         //     this.totalCountReductors = respones.data.totalCount;
  //         //     this.foundProductsReducers = filteredProducts;
  //         // }
  //         if (filteredProducts.length > 0 && filteredProducts2.length > 0) {

  //           this.foundProductsReducers = respones.data.content.filter(product =>
  //               product.reducer !== null && product.motor == null
  //           );

  //           this.logger.log("Data searchReducers", this.foundProductsMotors);
  //           this.logger.log("respones searchReducers", respones);
  //           this.totalCountReductors = respones.data.totalCount;
  //       }
  //         else {
  //             this.logger.log("No valid products found");
  //             alert("Редукторы не найдены");
  //             this.foundProductsReducers = [];
  //         }
  //     } else {
  //         this.logger.log("Response data is null");
  //         alert("Список пуст");
  //         this.foundProductsReducers = [];
  //     }
  // });
  // }


  searchReducers(filter2: Filter, page: number){
    let filter = new Filter();
    if (this.diamOutput == 0){
      filter.diamOutput == undefined;
    }
    else{
      filter.diamOutput = this.diamOutput;
    }
    filter.diamOutputAllowance = 0;
    if (this.ratio == 0){
      filter.ratio = undefined;
    }
    else {
      filter.ratio = this.ratio;
    }

    filter.idReducerType = this.reducerTypeId
    filter.idReducerSize = this.reducerSizeId;
    filter.idReducerInputType = this.reducerInputId;
    filter.idReducerMounting = this.reducerMountingId;
    filter.idReducerOutputShaftType = this.reducerOutShaftId;
    filter.idReducerInstallationType = this.reducerInstallId;
    filter.productTypeId = 2;
    this.logger.log('filter', filter);
    this.newFilterReductor = filter;
    this.productService.postPageFilter(filter, page).subscribe((respones: ResponseInfo<Page<Product>>)=>{
      if(respones.data !== null){
        this.logger.log("Data searchProduct", respones.data.content);
        this.logger.log("respones searchProduct", respones);
        this.totalCountReductors = respones.data.totalCount;
        this.foundProductsReducers = respones.data.content;
        this.logger.log(" totalCount", respones.data.totalCount);
      } else {
        alert(JSON.stringify(respones.errorMsg))
      }
    });
  }

  onPageChangeReducers(event: any){
    this.logger.log("event.page", event.page);
    this.searchReducers(this.newFilterMotors,event.page);
  }


  selectReducers(product: Product){
    this.selectedReducerId = product.reducer?.idReducer;
    this.selectRed = true;
    this.totalCountReductors = 1;
    this.foundProductsReducers = [];
    this.foundProductsReducers.push(product);
    this.priceReducers = product.price;
    this.weightReducers = product.weight;
    this.price = this.priceMotors + this.priceReducers;
    this.weight = this.weightMotors + this.weightReducers;
  }

  deleteSelectReducers(product: Product){
    this.selectedReducerId = null;
    this.selectRed = false;
    this.totalCountReductors = 0;
    this.foundProductsReducers = [];
    this.priceReducers = 0;
    this.weightReducers = 0;
    this.price = this.priceMotors + this.priceReducers;
    this.weight = this.weightMotors + this.weightReducers;

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
      //console.error('Такой тип двигателя не найден');
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
      //console.error('Такой фланц двигателя не найден');
      this.motorAdapterTypeId = undefined;
       this.logger.log('undefined выбранного фланца двигателя:', this.motorAdapterTypeId);
    }
  }

  // frequencySelected(event: Event) {
  //   const selectedElement = event.target as HTMLSelectElement;
  //   const selectedValue = selectedElement.value;
  //   this.logger.log('Выбранное значение frequency:', selectedValue);
  //   const intselectedValue: number = parseInt(selectedValue, 10);
  //   this.logger.log('Выбранное значение int frequency:', selectedValue);

  //   if (intselectedValue) {
  //     this.newMotor.frequency = intselectedValue;
  //   }
  //   if(selectedValue == "Select"){
  //     this.newMotor.frequency = undefined;
  //      this.logger.log('undefined значение frequency:', this.newMotor.frequency);
  //   }
  // }

  polesSelected(event: Event){
    const selectedElement = event.target as HTMLSelectElement;
      const selectedValue = selectedElement.value;
      this.logger.log('Выбранное значение poles:', selectedValue);
      const intselectedValue: number = parseInt(selectedValue, 10);
      this.logger.log('Выбранное значение int poles:', selectedValue);

      if (intselectedValue) {
        this.newMotor.polesNumber = intselectedValue;
        this.selectedPoles = intselectedValue;
      }
      if(selectedValue == "Select"){
        this.newMotor.polesNumber = undefined;
        this.selectedPoles = undefined;
         this.logger.log('undefined значение poles:', this.newMotor.polesNumber);
      }
  }



  getAllReducerType() {
    this.reducerService.getAllReducerTypes().subscribe((respones: ResponseInfo<ReducerType[]>) => {
      if(respones.data !== null){
        this.logger.log("Data getAllReducerType: ", respones.data);
        this.reducerType = respones.data;
      } else {
        alert(JSON.stringify(respones.errorMsg))
      }
    });
  }

  idReducerTypeSelected(event: Event) {
    const selectedElement = event.target as HTMLSelectElement;
    const selectedValue = selectedElement.value;
    this.logger.log('Выбранное значение reducerType:', selectedValue);
    const selectedReducer = this.reducerType.find(type => type.reducerTypeName === selectedValue);

    if (selectedReducer) {
      this.reducerTypeId = selectedReducer.idReducerType;
      this.newReducer.reducerTypeId = selectedReducer.idReducerType;
      this.logger.log('ID выбранного типа редуктора:', this.reducerTypeId);
      this.getReducerSizeByReducerTypeId(this.reducerTypeId);
      this.getReducerInputByReducerTypeId(this.reducerTypeId);
      //this.getReducerAdapterByReducerTypeId(this.reducerTypeId);
      this.getReducerOutputShaftTypeByReducerTypeId(this.reducerTypeId);
      this.getReducerInstallationByReducerTypeId(this.reducerTypeId);
    } else {
      //console.error('Такой тип редуктора не найден');
      this.reducerTypeId = undefined;
      this.newReducer.reducerTypeId = undefined;
      this.logger.log('undefined id выбранного типа редуктора:', this.reducerTypeId);
    }
  }

  getReducerInputByReducerTypeId(id:number) {
    this.reducerService.getReducerInputByReducerTypeId(id).subscribe((respones: ResponseInfo<ReducerInputType[]>)=>{
      if(respones.data !== null){
        this.logger.log("Data getReducerInputByReducerTypeId", respones.data);
        this.reducerInputType = respones.data;
      } else {
        alert(JSON.stringify(respones.errorMsg))
      }
    });
  }

  idReducerInputSelected(event: Event) {
    const selectedElement = event.target as HTMLSelectElement;
    const selectedValue = selectedElement.value;
    this.logger.log('Выбранное значение ReducerInputType:', selectedValue);
    const selectedInput= this.reducerInputType.find(type => type.reducerInputTypeValue === selectedValue);

    if (selectedInput) {
      this.newReducer.reducerInputTypeId = selectedInput.idReducerInputType;
      this.reducerInputId = selectedInput.idReducerInputType;
      this.logger.log('ID выбранного типа входа:', selectedInput.idReducerInputType);
    } else {
      //console.error('Такого типа входа не найдено');
      this.newReducer.reducerInputTypeId = undefined;
      this.reducerInputId = undefined;
      this.logger.log('undefined выбранного типа входа:', this.newReducer.reducerInputTypeId);
    }
  }

  getReducerAdapterByReducerTypeId(id:number) {
    this.reducerService.getReducerAdapterByReducerTypeId(id).subscribe((respones: ResponseInfo<ReducerAdapterType[]>)=>{
      if(respones.data !== null){
        this.logger.log("Data getReducerAdapterByReducerTypeId", respones.data);
        this.reducerAdapterType = respones.data;
      } else {
        alert(JSON.stringify(respones.errorMsg))
      }
    });
  }

  getReducerOutputShaftTypeByReducerTypeId(id:number) {
    this.reducerService.getReducerOutputShaftTypeByReducerTypeId(id).subscribe((respones: ResponseInfo<ReducerOutputShaftType[]>)=>{
      if(respones.data !== null){
        this.logger.log("Data getReducerOutputShaftTypeByReducerTypeId", respones.data);
        this.reducerOutputShaftType = respones.data;
      } else {
        alert(JSON.stringify(respones.errorMsg))
      }
    });
  }

  idReducerOutputShaftTypeSelected(event: Event) {
    const selectedElement = event.target as HTMLSelectElement;
    const selectedValue = selectedElement.value;
    this.logger.log('Выбранное значение ReducerOutputShaftType:', selectedValue);
    const selectedOutputShaft= this.reducerOutputShaftType.find(type => type.reducerOutputShaftTypeValue === selectedValue);

    if (selectedOutputShaft) {
      this.newReducer.reducerOutputShaftTypeId = selectedOutputShaft.idReducerOutputShaftType;
      this.reducerOutShaftId = selectedOutputShaft.idReducerOutputShaftType;
      this.logger.log('ID выбранноой формы выходного вала:', selectedOutputShaft.idReducerOutputShaftType);
    } else {
      //console.error('Такой формы не найдено');
      this.newReducer.reducerOutputShaftTypeId = undefined;
      this.reducerOutShaftId = undefined;
      this.logger.log('undefined выбранноой формы выходного вала:', this.newReducer.reducerOutputShaftTypeId);
    }
  }

  getReducerInstallationByReducerTypeId(id:number) {
    this.reducerService.getReducerInstallationByReducerTypeId(id).subscribe((respones: ResponseInfo<ReducerInstallationType[]>)=>{
      if(respones.data !== null){
        this.logger.log("Data getReducerInstallationByReducerTypeId", respones.data);
        this.reducerInstallationType = respones.data;
      } else {
        alert(JSON.stringify(respones.errorMsg))
      }
    });
  }

  idReducerInstallationSelected(event: Event) {
    const selectedElement = event.target as HTMLSelectElement;
    const selectedValue = selectedElement.value;
    this.logger.log('Выбранное значение ReducerInstallationType:', selectedValue);
    const selectedInstallation= this.reducerInstallationType.find(type => type.reducerInstallationTypeValue === selectedValue);

    if (selectedInstallation) {
      this.newReducer.reducerInstallationTypeId = selectedInstallation.idReducerInstallationType;
      this.reducerInstallId =  selectedInstallation.idReducerInstallationType;
      this.logger.log('ID выбранного типа крепления:', selectedInstallation.idReducerInstallationType);
    } else {
      //console.error('Такое крепление не найдено');
      this.newReducer.reducerInstallationTypeId = undefined;
      this.reducerInstallId  = undefined;
      this.logger.log('undefined выбранного типа крепления:', this.newReducer.reducerInstallationTypeId);
    }
  }

  getAllReducerMounting(){
    this.reducerService.getAllReducerMounting().subscribe((respones: ResponseInfo<ReducerMounting[]>) => {
      if(respones.data !== null){
        this.logger.log("Data getAllReducerMounting: ", respones.data);
        this.reducerMounting = respones.data;
      } else {
        alert(JSON.stringify(respones.errorMsg))
      }
    });
  }

  idReducerMountingSelected(event: Event) {
    const selectedElement = event.target as HTMLSelectElement;
    const selectedValue = selectedElement.value;
    this.logger.log('Выбранное значение ReducerMounting:', selectedValue);
    const selectedMounting= this.reducerMounting.find(type => type.reducerMountingValue === selectedValue);

    if (selectedMounting) {
      this.newReducer.reducerMountingId = selectedMounting.idReducerMounting;
      this.reducerMountingId = selectedMounting.idReducerMounting;
      this.logger.log('ID выбранного монтажного положения:', selectedMounting.idReducerMounting);
    } else {
      //console.error('Такое положение не найдено');
      this.newReducer.reducerMountingId = undefined;
      this.reducerMountingId = undefined;
      this.logger.log('undefined выбранного монтажного положения:', this.newReducer.reducerMountingId);
    }
  }

  getReducerSizeByReducerTypeId(id:number) {
    this.reducerService.getReducerSizeByReducerTypeId(id).subscribe((respones: ResponseInfo<ReducerSize[]>)=>{
      if(respones.data !== null){
        this.logger.log("Data getResucerSizeByMotorTypeId", respones.data);
        this.resucerSize = respones.data;
      } else {
        alert(JSON.stringify(respones.errorMsg))
      }
    });
  }

  idReducerSizeBSelected(event: Event) {
    const selectedElement = event.target as HTMLSelectElement;
    const selectedValue = selectedElement.value;
    this.logger.log('Выбранное значение ReducerSize:', selectedValue);
    const selectedSize = this.resucerSize.find(type => type.reducerSizeValue === selectedValue);

    if (selectedSize) {
      this.newReducer.reducerSizeId = selectedSize.idReducerSize;
      this.reducerSizeId = selectedSize.idReducerSize;
      this.logger.log('ID выбранного размера редуктора:', selectedSize.idReducerSize);
    } else {
      //console.error('Такой размер не найден');
      this.newReducer.reducerSizeId = undefined;
      this.reducerSizeId = undefined;
      this.logger.log('undefined выбранного размера редуктора:', this.newReducer.reducerSizeId);
    }
  }

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

  getByProductTypeOptionId(id:number) {
    this.productService.getByProductTypeOptionId(id).subscribe((respones: ResponseInfo<ProductOption[]>)=>{
      if(respones.data !== null){
        this.logger.log("Data getByProductTypeOptionId motor-reducer", respones.data);
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

  preventNegative(event: KeyboardEvent): void {
    if (event.key === '-' || event.key === 'e') {
      event.preventDefault();
    }
  }

  dynamicAddProduct(){
    this.newProduct.productTypeId = this.idProductType;

    if(this.selectedMotorId && this.selectedMotorId !== null && this.selectedReducerId !== undefined){
      this.newProduct.motorId = this.selectedMotorId;
      this.newProduct.motor = null;
    }
    else{
      this.newProduct.motorId = null;
      this.newMotor.power = this.power;
      this.newMotor.efficiency = this.efficiency;
      this.newMotor.ratedCurrent = this.ratedCurrent;
      this.newMotor.momentOfInertia = this.momentOfInertia;
      this.newMotor.momentOfInertia = this.momentOfInertia;
      this.newMotor.motorTypeId = this.motorTypeId;
      this.newMotor.motorAdapterTypeId = this.motorAdapterTypeId;
      this.newProduct.motor = this.newMotor;
    }

    if(this.selectedReducerId && this.selectedReducerId !== null && this.selectedReducerId !== undefined){
      this.newProduct.reducerId = this.selectedReducerId;
      this.newProduct.reducer = null;
    }
    else{
      this.newProduct.reducerId = null;
      this.newReducer.ratio = this.ratio;
      this.newReducer.diameterOutputShaft = this.diamOutput;
      this.newProduct.reducer = this.newReducer;
    }

    this.newProduct.rpm = this.rpm;
    this.newProduct.name = this.name;
    this.newProduct.weight = this.weight;
    this.newProduct.torqueMoment = this.torqueMoment;
    this.newProduct.price = this.price;
    this.newProduct.serviceFactor = this.serviceFactor;
    this.logger.log('dynamicProduct', this.newProduct);
    this.dynamicProduct.emit(this.newProduct);
  }

}
