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
  reducerTypeId: number;
  resucerSize: ReducerSize[];
  reducerOutputShaftType: ReducerOutputShaftType[];
  reducerInstallationType: ReducerInstallationType[];
  reducerMounting: ReducerMounting[];
  filter: Filter = new Filter();
  foundProducts: Product[];
  options: number[] = [];
  productOption: ProductOption[];

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
    }
  }

  getAllReducerType() {
    this.reducerService.getAllReducerTypes().subscribe(
      (respones: ResponseInfo<ReducerType[]>) => {
        console.log("Data getAllReducerType: ", respones.data);
        this.reducerType = respones.data;
      },
      (exepcion: any) => {
        console.error("Error getAllReducerType:", exepcion.error);
      }
    );
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
    }
  }

  getReducerSizeByReducerTypeId(id:number) {
    this.reducerService.getReducerSizeByReducerTypeId(id).subscribe(
      (respones: ResponseInfo<ReducerSize[]>)=>{
        console.log("Data getResucerSizeByMotorTypeId", respones.data);
        this.resucerSize = respones.data;
      },
      (error:any) =>{
        console.error("Error getResucerSizeByMotorTypeId:", error);
      }
    );
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
    }
  }

  getReducerOutputShaftTypeByReducerTypeId(id:number) {
    this.reducerService.getReducerOutputShaftTypeByReducerTypeId(id).subscribe(
      (respones: ResponseInfo<ReducerOutputShaftType[]>)=>{
        console.log("Data getReducerOutputShaftTypeByReducerTypeId", respones.data);
        this.reducerOutputShaftType = respones.data;
      },
      (error:any) =>{
        console.error("Error getReducerOutputShaftTypeByReducerTypeId:", error);
      }
    );
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
    }
  }


  getReducerInstallationByReducerTypeId(id:number) {
    this.reducerService.getReducerInstallationByReducerTypeId(id).subscribe(
      (respones: ResponseInfo<ReducerInstallationType[]>)=>{
        console.log("Data getReducerInstallationByReducerTypeId", respones.data);
        this.reducerInstallationType = respones.data;
      },
      (error:any) =>{
        console.error("Error getReducerInstallationByReducerTypeId:", error);
      }
    );
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
    }
  }


  getAllReducerMounting(){
    this.reducerService.getAllReducerMounting().subscribe(
      (respones: ResponseInfo<ReducerMounting[]>) => {
        console.log("Data getAllReducerMounting: ", respones.data);
        this.reducerMounting = respones.data;
      },
      (exepcion: any) => {
        console.error("Error getAllReducerMounting:", exepcion.error);
      }
    );
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
    filter.power = this.power;
    filter.diamOutput = this.diamOutput;
    filter.diamOutputAllowance = this.diamOutputAllowance;
    filter.ratio = this.ratio;
    filter.torqueMoment = this.torqueMoment;
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

  getByProductTypeOptionId(id:number) {
    this.productService.getByProductTypeOptionId(id).subscribe(
      (respones: ResponseInfo<ProductOption[]>)=>{
        console.log("Data getByProductTypeOptionId motor-reducer", respones.data);
        this.productOption = respones.data;
      },
      (error:any) =>{
        console.error("Error getByProductTypeOptionId motor-reducer:", error);
      }
    );
  }

  downloadImage(id:number,filename: string){
    this.productService.downloadImageById(id,filename);
  }

}
