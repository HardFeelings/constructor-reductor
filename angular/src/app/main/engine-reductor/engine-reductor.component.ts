import { ProductService } from 'src/app/sevices/product.service';
import { Component, Input } from '@angular/core';
import { ReducerService } from 'src/app/sevices/reducer.service';
import { MotorService } from 'src/app/sevices/motor.service';
import { EngineType } from 'src/app/models/engine';
import { ResponseInfo } from 'src/app/models/responesInfo';
import { ReducerInstallationType, ReducerMounting, ReducerOutputShaftType, ReducerSize, ReducerType } from 'src/app/models/reducer';
import { Filter } from 'src/app/models/filter';
import { ProductOption,Product } from 'src/app/models/product';


@Component({
  selector: 'app-engine-reductor',
  templateUrl: './engine-reductor.component.html',
  styleUrls: ['./engine-reductor.component.scss']
})
export class EngineReductorComponent {
  @Input() idProductType: number;
  diamOutput!: number;
  diamOutputAllowance!: number;
  torqueMoment!: number;
  ratio!: number;
  power!: number;
  motorType: EngineType[];
  reducerType: ReducerType[];
  reducerTypeId: number | undefined;
  resucerSize: ReducerSize[];
  reducerOutputShaftType: ReducerOutputShaftType[];
  reducerInstallationType: ReducerInstallationType[];
  reducerMounting: ReducerMounting[];
  filter: Filter = new Filter();
  foundProducts: Product[];
  options: number[] = [];
  productOption: ProductOption[];

  rpm!:number;

  constructor(private reducerService: ReducerService, private productService: ProductService, private motorService: MotorService){
  }

  ngOnInit() {
    this.getAllMotorType();
    this.getAllReducerType();
    this.getAllReducerMounting();
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

  idMotorTypeSelected(event: Event) {
    const selectedElement = event.target as HTMLSelectElement;
    const selectedValue = selectedElement.value;
    console.log('Выбранное значение MotorType:', selectedValue);
    const selectedMotor = this.motorType.find(type => type.motorTypeName === selectedValue);

    if (selectedMotor) {
      this.filter.motorTypeId = selectedMotor.idMotorType;
      console.log('ID выбранного типа двигателя:', selectedMotor.idMotorType);
    } else {
      console.error('Такой тип двигателя не найден');
      this.filter.motorTypeId = undefined;
      console.log('undefined выбранного типа двигателя:', this.filter.motorTypeId);
    }
  }


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
      this.filter.idReducerType = selectedReducer.idReducerType;
      console.log('ID выбранного типа редуктора:', this.reducerTypeId);
      this.getReducerSizeByReducerTypeId(this.reducerTypeId);
      this.getReducerOutputShaftTypeByReducerTypeId(this.reducerTypeId);
      this.getReducerInstallationByReducerTypeId(this.reducerTypeId);
    } else {
      console.error('Такой тип редуктора не найден');
      this.reducerTypeId = undefined;
      this.filter.idReducerType = undefined;
      console.log('undefined выбранного типа редуктора:', this.filter.idReducerType);
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
      this.filter.idReducerSize = selectedSize.idReducerSize;
      console.log('ID выбранного размера редуктора:', selectedSize.idReducerSize);
    } else {
      console.error('Такой размер не найден');
       this.filter.idReducerSize = undefined;
      console.log('undefined выбранного размера редуктора:', this.filter.idReducerSize);
    }
  }

  getReducerOutputShaftTypeByReducerTypeId(id:number) {
    this.reducerService.getReducerOutputShaftTypeByReducerTypeId(id).subscribe((respones: ResponseInfo<ReducerOutputShaftType[]>)=>{
      if(respones.data !== undefined){
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
      this.filter.idReducerOutputShaftType = selectedOutputShaft.idReducerOutputShaftType;
      console.log('ID выбранноой формы выходного вала:', selectedOutputShaft.idReducerOutputShaftType);
    } else {
      console.error('Такой формы не найдено');
      this.filter.idReducerOutputShaftType = undefined;
      console.log('undefined выбранноой формы выходного вала:', this.filter.idReducerOutputShaftType);
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
      this.filter.idReducerInstallationType = selectedInstallation.idReducerInstallationType;
      console.log('ID выбранного типа крепления:', selectedInstallation.idReducerInstallationType);
    } else {
      console.error('Такое крепление не найдено');
      this.filter.idReducerInstallationType = undefined;
      console.log('undefined выбранного типа крепления:',this.filter.idReducerInstallationType);
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
      this.filter.idReducerMounting = selectedMounting.idReducerMounting;
      console.log('ID выбранного монтажного положения:', selectedMounting.idReducerMounting);
    } else {
      console.error('Такое положение не найдено');
      this.filter.idReducerMounting = undefined;
      console.log('undefined выбранного монтажного положения:',   this.filter.idReducerMounting);
    }
  }


  onCheckboxChange(event: Event, optionId: number) {
    const target = event.target as HTMLInputElement;
    if (target.checked) {
      this.options.push(optionId);
      this.filter.productOptions = this.options;
      console.log(`Checkbox with id ${optionId} is checked.`);
      console.log(this.filter.productOptions);
    } else {
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
    filter.rpm = this.rpm;
    filter.power = this.power;
    filter.diamOutput = this.diamOutput;
    filter.diamOutputAllowance = this.diamOutputAllowance;
    filter.ratio = this.ratio;
    filter.torqueMoment = this.torqueMoment;
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

  downloadImage(id:number,filename: string){
    this.productService.downloadImageById(id,filename);
  }

  preventNegative(event: KeyboardEvent): void {
    if (event.key === '-' || event.key === 'e') {
      event.preventDefault();
    }
  }


}
