import { Component, Input } from '@angular/core';
import { EngineAdapterType, EngineType } from 'src/app/models/engine';
import { Filter } from 'src/app/models/filter';
import { Product, ProductOption } from 'src/app/models/product';
import { ResponseInfo } from 'src/app/models/responesInfo';
import { MotorService } from 'src/app/sevices/motor.service';
import { ProductService } from 'src/app/sevices/product.service';
import { MatDialog } from '@angular/material/dialog';
import { EmailComponent } from '../email/email.component';
import { Page } from 'src/app/models/page';
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

  constructor(private logger: NGXLogger,private motorService: MotorService, private productService: ProductService, public dialog: MatDialog){
  }

  ngOnInit() {
    this.getAllMotorType();
    this.getByProductTypeOptionId(this.idProductType);
    this.filter.productTypeId = this.idProductType;
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

  idMotorTypeSelected(event: Event) {
    const selectedElement = event.target as HTMLSelectElement;
    const selectedValue = selectedElement.value;
    this.logger.log('Выбранное значение MotorType:', selectedValue);
    const selectedMotor = this.motorType.find(type => type.motorTypeName === selectedValue);

    if (selectedMotor) {
      this.motorTypeId = selectedMotor.idMotorType;
      this.filter.motorTypeId = selectedMotor.idMotorType;
      this.logger.log('ID выбранного типа двигателя:', this.motorTypeId);
      if(selectedMotor.idMotorType && selectedMotor.idMotorType !== 1){
        this.getMotorAdapterByMotorTypeId(this.motorTypeId);
      }
    } else {
      console.error('Такой тип двигателя не найден');
      this.motorTypeId = undefined;
      this.filter.motorTypeId = undefined;
      this.logger.log('undefined выбранного типа двигателя:', this.filter.motorTypeId);
    }
  }

  idMotorAdapterTypeSelected(event: Event) {
    const selectedElement = event.target as HTMLSelectElement;
    const selectedValue = selectedElement.value;
    this.logger.log('Выбранное значение AdapterType:', selectedValue);
    const selectedAdapter = this.engineAdapterTypeByMotorTypeId.find(type => type.motorAdapterTypeValue === selectedValue);

    if (selectedAdapter) {
      this.filter.motorAdapterTypeId = selectedAdapter.idMotorAdapterType;
      this.logger.log('ID выбранного фланца двигателя:', selectedAdapter.idMotorAdapterType);
    } else {
      console.error('Такой фланц двигателя не найден');
      this.filter.motorAdapterTypeId = undefined;
      this.logger.log('undefined выбранного фланца двигателя:',  this.filter.motorAdapterTypeId);
    }
  }

  onCheckboxChange(event: Event, optionId: number) {
    const target = event.target as HTMLInputElement;
    if (target.checked) {
      this.options.push(optionId);
      this.filter.productOptions = this.options;
      this.logger.log(`Checkbox with id ${optionId} is checked.`);
      this.logger.log(this.filter.productOptions);
    }
    else {
      const index = this.options.indexOf(optionId);
      if (index !== -1) {
        this.options.splice(index, 1);
        this.filter.productOptions = this.options;
      }
      this.logger.log(`Checkbox with id ${optionId} is unchecked.`);
      this.logger.log(this.filter.productOptions);
    }
  }

  onPageChange(event: any){
    this.logger.log("event.page", event.page);
    this.searchProduct(this.newFilter,event.page);
  }

  searchProduct(filter: Filter, page: number){
    filter.power = this.power;
    filter.rpm = this.rpm
    this.logger.log('filter', filter);
    this.newFilter = filter;
    this.productService.postPageFilter(filter, page).subscribe((respones: ResponseInfo<Page<Product>>)=>{
      if(respones.data !== null){
        this.logger.log("Data searchProduct", respones.data.content);
        this.logger.log("respones searchProduct", respones);
        this.totalCount = respones.data.totalCount;
        this.foundProducts = respones.data.content;
        this.logger.log(" totalCount", respones.data.totalCount);
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

  goSendEmail(name:string){
    const dialogAddingNewStudent = this.dialog.open(EmailComponent, {
      width: '600px',
      height: '550px',
      data: name,
    });
    dialogAddingNewStudent.afterClosed().subscribe((result: boolean) => {
      if(result  !== null && result  !== undefined || result == true) {
        this.logger.log('dialog goSendEmail', result);
        this.ngOnInit();
      } else{
        this.logger.log('Окно закрыто без изменений');
        this.ngOnInit();
      }

    });
  }

}
