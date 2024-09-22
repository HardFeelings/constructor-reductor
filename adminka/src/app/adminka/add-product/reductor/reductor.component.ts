import { Component, Input, Output, EventEmitter } from '@angular/core';
import { Reducer, ReducerAdapterType, ReducerInputType, ReducerInstallationType, ReducerMounting, ReducerOutputShaftType, ReducerSize, ReducerType } from 'src/app/models/reducer';
import { ProductOption,Product } from 'src/app/models/product';
import { ResponseInfo } from 'src/app/models/responesInfo';
import { ReducerService } from 'src/app/services/reducer.service';
import { ProductService } from 'src/app/services/product.service';
import { NGXLogger } from "ngx-logger";


@Component({
  selector: 'app-reductor',
  templateUrl: './reductor.component.html',
  styleUrls: ['./reductor.component.scss']
})
export class ReductorComponent {
  @Input() idProductType: number;
  @Output() dynamicProduct = new EventEmitter<Product>();
  reducerType: ReducerType[];
  reducerTypeId: number | undefined;
  productOption: ProductOption[];
  resucerSize: ReducerSize[];
  reducerMounting: ReducerMounting[];
  reducerInputType: ReducerInputType[];
  reducerAdapterType: ReducerAdapterType[];
  reducerOutputShaftType: ReducerOutputShaftType[];
  reducerInstallationType: ReducerInstallationType[];
  options: number[] = [];
  rpmArray: number[]=[750, 1000, 1500, 3000];
  diamInput: number;
  diamInputAllowance:number;
  diamOutput: number;
  diamOutputAllowance: number;
  ratio: number;
  name!: string;
  weight!: number;
  price!:number;
  rpm!:number;
  torqueMoment!:number;
  serviceFactor!:number;
  newProduct!:Product;
  newReducer!:Reducer;

  constructor(private logger: NGXLogger,private reducerService: ReducerService, private productService: ProductService){
    this.newProduct = new Product;
    this.newReducer = new Reducer;
  }

  ngOnInit(){
    this.getAllReducerType();
    this.getAllReducerMounting();
    this.getByProductTypeOptionId(this.idProductType);
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
      this.getReducerAdapterByReducerTypeId(this.reducerTypeId);
      this.getReducerOutputShaftTypeByReducerTypeId(this.reducerTypeId);
      this.getReducerInstallationByReducerTypeId(this.reducerTypeId);
    } else {
      console.error('Такой тип редуктора не найден');
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

  preventNegative(event: KeyboardEvent): void {
    if (event.key === '-' || event.key === 'e') {
      event.preventDefault();
    }
  }

  idReducerInputSelected(event: Event) {
    const selectedElement = event.target as HTMLSelectElement;
    const selectedValue = selectedElement.value;
    this.logger.log('Выбранное значение ReducerInputType:', selectedValue);
    const selectedInput= this.reducerInputType.find(type => type.reducerInputTypeValue === selectedValue);

    if (selectedInput) {
      this.newReducer.reducerInputTypeId = selectedInput.idReducerInputType;
      this.logger.log('ID выбранного типа входа:', selectedInput.idReducerInputType);
    } else {
      console.error('Такого типа входа не найдено');
      this.newReducer.reducerInputTypeId = undefined;
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
      this.logger.log('ID выбранноой формы выходного вала:', selectedOutputShaft.idReducerOutputShaftType);
    } else {
      console.error('Такой формы не найдено');
      this.newReducer.reducerOutputShaftTypeId = undefined;
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
      this.logger.log('ID выбранного типа крепления:', selectedInstallation.idReducerInstallationType);
    } else {
      console.error('Такое крепление не найдено');
      this.newReducer.reducerInstallationTypeId = undefined;
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
      this.logger.log('ID выбранного монтажного положения:', selectedMounting.idReducerMounting);
    } else {
      console.error('Такое положение не найдено');
      this.newReducer.reducerMountingId = undefined;
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
      this.logger.log('ID выбранного размера редуктора:', selectedSize.idReducerSize);
    } else {
      console.error('Такой размер не найден');
      this.newReducer.reducerSizeId = undefined;
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

  dynamicAddProduct(){
    this.newReducer.ratio = this.ratio;
    this.newReducer.diameterOutputShaft = this.diamOutput;
    this.newProduct.productTypeId = this.idProductType;
    this.newProduct.reducer = this.newReducer;
    this.newProduct.rpm = this.rpm;
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
        this.logger.log("Data getByProductTypeOptionId reducer", respones.data);
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
