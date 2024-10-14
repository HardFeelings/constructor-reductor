import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Motor, MotorAdapterType, MotorType } from '../classes/motor';
import { Product, ProductOption, ProductType } from '../classes/product';
import { MountingPoint, Reducer, ReducerAdapterType, ReducerInputType, ReducerInstallationType, ReducerOutputShaftType, ReducerSize, ReducerType } from '../classes/reducer';
import { ImageService } from '../services/image.service';
import { Manager } from '../classes/manager';
import { AddProductComponent } from './add-product/add-product.component';
import { MatDialog } from '@angular/material/dialog';
import { ProductService } from '../services/product.service';
import { ResponseInfo } from '../models/responesInfo';
import { DeleteComponent } from '../delete/delete.component';
import { PaymentTerms } from '../classes/paymentTerm';
import { MotorService } from '../services/motor.service';
import { Page } from '../models/page';
import { ReducerService } from '../services/reducer.service';
import { ManagerService } from '../services/manager.service';
import { NGXLogger } from "ngx-logger";
import { Router } from '@angular/router';
import { EmployeeService } from '../services/employee.service';
// import { Employee } from '../models/employee';
import { Employee } from '../classes/employee';

@Component({
  selector: 'app-adminka',
  templateUrl: './adminka.component.html',
  styleUrls: ['./adminka.component.scss']
})
export class AdminkaComponent {
  id = 0
  paymentTerms_list: PaymentTerms[];
  motor_list: Motor[]
  motorType_list: MotorType[]
  motorAdapterType_list: MotorAdapterType[]
  product_list: Product[]
  productType_list: ProductType[]
  productOption_list: ProductOption[]
  reducer_list: Reducer[]
  reducerType_list: ReducerType[]
  reducerSize_list: ReducerSize[]
  reducerOutput_list: ReducerOutputShaftType[]
  reducerMounting_list: MountingPoint[]
  reducerInstallationType_list: ReducerInstallationType[]
  reducerInputType_list: ReducerInputType[]
  manager_list:Manager[];
  employee_list:Employee[];

  searchData: string | null = null;
  // totalCount: number;
  totalCountProductOption: number;
  firstProductOption: number = 0;
  rowsProductOption: number = 15;

  totalCountReducer: number;
  firstReducer: number = 0;
  rowsReducer: number = 15;

  totalCountReducerSize: number;
  firstReducerSize: number = 0;
  rowsReducerSize: number = 15;

  totalCountReducerOutputShaft: number;
  firstReducerOutputShaft: number = 0;
  rowsReducerOutputShaft: number = 15;

  totalCountReducerInstallationType:number;
  firstReducerInstallationType:number = 0;
  rowsReducerInstallationType:number = 15;

  totalCountReducerInputType: number;
  firstReducerInputType: number = 0;
  rowsReducerInputType: number = 15;

  totalCountMotorList:number;
  firstMotorList:number = 0;
  rowsMotorList:number = 15;

  totalCountProductList: number;
  firstProductList: number = 0;
  rowsProductList: number = 15;


  firstSearchList: number = 0;
  rowsSearchList: number = 15;
  totalCountSearchList: number;
  isSearchMode = false;

  totalCountMotorAdapterTypeList:number;
  firstMotorAdapterTypeList:number = 0;
  rowsMotorAdapterTypeList:number = 15;

  totalCountListManagers:number;
  firstListManagers:number = 0;
  rowsListManagers:number = 15;

  totalCountListEmployees:number;
  firstListEmployees:number = 0;
  rowsListEmployees:number = 15;


  page: number = 0;
  page2: number = 0;
  page3: number = 0;
  page5: number = 0;
  page6: number = 0;
  page8: number = 0;
  page9: number = 0;
  page11: number = 0;
  page12: number = 0;
  page13: number = 0;
  page15: number = 0;

  constructor(private http: HttpClient, private  router: Router, private employeeService: EmployeeService ,private imageService: ImageService, private logger: NGXLogger, public dialog:MatDialog, private motorService: MotorService, private productService: ProductService, private reducerService: ReducerService, private managerService: ManagerService) {
    this.paymentTerms_list = new Array<PaymentTerms>();
    this.motor_list = new Array<Motor>();
    this.motorType_list = new Array<MotorType>();
    this.motorAdapterType_list = new Array<MotorAdapterType>();
    this.product_list = new Array<Product>();
    this.productType_list = new Array<ProductType>();
    this.productOption_list = new Array<ProductOption>();
    this.reducer_list = new Array<Reducer>();
    this.reducerType_list = new Array<ReducerType>();
    this.reducerSize_list = new Array<ReducerSize>();
    this.reducerOutput_list = new Array<ReducerOutputShaftType>();
    this.reducerMounting_list = new Array<MountingPoint>();
    this.reducerInstallationType_list = new Array<ReducerInstallationType>();
    this.reducerInputType_list = new Array<ReducerInputType>();
    this.manager_list = new Array<Manager>();
  }

  setid(id: number) {
    this.id = id;
    localStorage.removeItem('id_page');
    localStorage.setItem('id_page', id.toString());
    const spans = document.querySelectorAll('.sidebar span');
    spans.forEach((span, index) => {
        if (index === id) {
            span.classList.add('selected');
        } else {
            span.classList.remove('selected');
        }
    });
  }

  ngOnInit() {
    this.getMotorList(0)
    this.getMotorTypeList()
    this.getMotorAdapterTypeList(0)
    this.getProductList(0)
    this.getProductType()
    this.getProductOption(0)
    this.getReducer(0)
    this.getReducerSize(0)
    this.getReducerType()
    this.getReducerOutputShaft(0)
    this.getReducerMounting()
    this.getReducerInstallationType(0)
    this.getReducerInputType(0)
    this.getListManagers(0)
    this.getListEmployees(0)
    this.getListPaymentTerms()
    const storedId = localStorage.getItem('id_page');
    if (storedId) {
      this.setid(parseInt(storedId, 10));
    }
    else{
      this.setid(0);
    }
  }

  onFileSelected(event: any, product: Product) {
    const selectedFile = event.target.files[0];
    const reader = new FileReader();

    reader.onload = (e: any) => {
      const base64Image = e.target.result;
      const base64WithoutPrefix = base64Image.split(',')[1];
      this.logger.log('base64:', base64WithoutPrefix);

      setTimeout(() => {
        product.imageString = base64WithoutPrefix;
        product.imageEmpty = false;
        product.imageChanged = true;
      }, 0);
    };

    reader.readAsDataURL(selectedFile);
  }

  openFilePicker(fileInput: HTMLInputElement) {
    if (fileInput) {
      fileInput.click();
    }
  }

  navigateToUrl() {
    this.router.navigate(['/commercial']);
  }

  onPageChange(event: any){
    this.logger.log("event.page", event.page);
    this.page = event.page;
    this.firstMotorList = event.first;
    this.rowsMotorList = event.rows;
    this.getMotorList(event.page);
    this.logger.log('getMotorList', this.id, event.page);
  }

  onPageChange2(event: any){
    this.logger.log("event.page", event.page);
    this.page2 = event.page;
    this.firstMotorAdapterTypeList = event.first;
    this.rowsMotorAdapterTypeList = event.rows;
    this.getMotorAdapterTypeList(event.page);
    this.logger.log('getMotorAdapterTypeList', this.id, event.page);
  }

  onPageChange3(event: any) {
    if (this.isSearchMode) {
      this.firstSearchList = event.first;
      this.rowsSearchList = event.rows;
      this.searchProductByName(event.page);
    } else {
      this.firstProductList = event.first;
      this.rowsProductList = event.rows;
      this.getProductList(event.page);
    }
  }

  onPageChange5(event: any){
    this.logger.log("event.page", event.page);
    this.page5 = event.page;
    this.firstProductOption = event.first;
    this.rowsProductOption = event.rows;
    this.getProductOption(event.page);
    this.logger.log('getProductOption', this.id, event.page);
  }

  onPageChange6(event: any){
    this.logger.log("event.page", event.page);
    this.page6 = event.page;
    this.firstReducer = event.first;
    this.rowsReducer = event.rows;
    this.getReducer(event.page);
    this.logger.log('getReducer', this.id, event.page);
  }

  onPageChange8(event: any){
    this.logger.log("event.page", event.page);
    this.page8 = event.page;
    this.firstReducerSize = event.first;
    this.rowsReducerSize = event.rows;
    this.getReducerSize(event.page);
    this.logger.log('getReducerSize', this.id, event.page);
  }


  onPageChange9(event: any){
    this.logger.log("event.page", event.page);
    this.page9 = event.page;
    this.firstReducerOutputShaft = event.first;
    this.rowsReducerOutputShaft = event.rows;
    this.getReducerOutputShaft(event.page);
    this.logger.log('getReducerOutputShaft', this.id, event.page);
  }


  onPageChange11(event: any){
    this.logger.log("event.page", event.page);
    this.page11 = event.page;
    this.firstReducerInstallationType = event.first;
    this.rowsReducerInstallationType = event.rows;
    this.getReducerInstallationType(event.page);
    this.logger.log('getReducerInstallationType', this.id, event.page);
  }

  onPageChange12(event: any){
    this.logger.log("event.page", event.page);
    this.page12 = event.page;
    this.firstReducerInputType = event.first;
    this.rowsReducerInputType = event.rows;
    this.getReducerInputType(event.page);
    this.logger.log('getReducerInputType', this.id, event.page);
  }

  onPageChange13(event: any){
    this.logger.log("event.page", event.page);
    this.page13 = event.page;
    this.firstListManagers= event.first;
    this.rowsListManagers = event.rows;
    this.getListManagers(event.page);
    this.logger.log('getListManagers', this.id, event.page);
  }

  onPageChange15(event: any){
    this.logger.log("event.page", event.page);
    this.page15 = event.page;
    this.firstListEmployees= event.first;
    this.rowsListEmployees = event.rows;
    this.getListEmployees(event.page);
    this.logger.log('getListEmployees', this.id, event.page);
  }


  oKDelete(id: number, i: any){
    const dialogAddingNewStudent = this.dialog.open(DeleteComponent, {
      width: '0px',
      height: '0px',
      data: id,
    });
    dialogAddingNewStudent.afterClosed().subscribe((okOrNot: boolean) => {
      if(okOrNot){
        if(this.id == 0){
          this.deleteMotor(i);
          this.logger.log('deleteMotor', this.id, i);
        }
        else if(this.id == 1){
          this.deleteMotorType(i);
          this.logger.log('deleteMotorType', this.id, i);
        }
        else if(this.id == 2){
          this.deleteMotorAdapterType(i);
          this.logger.log('deleteMotorAdapterType', this.id, i);
        }
        else if(this.id == 3){
          this.deleteProduct(i);
          this.logger.log('deleteProduct', this.id, i);
        }
        else if(this.id == 4){
          this.deleteProductType(i);
          this.logger.log('deleteProductType', this.id, i);
        }
        else if(this.id == 5){
          this.deleteProductOption(i);
          this.logger.log('deleteProductOption', this.id, i);
        }
        else if(this.id == 6){
          this.deleteReducer(i);
          this.logger.log('deleteReducer', this.id, i);
        }
        else if(this.id == 7){
          this.deleteReducerType(i);
          this.logger.log('deleteReducerType', this.id, i);
        }
        else if(this.id == 8){
          this.deleteReducerSize(i);
          this.logger.log('deleteReducerSize', this.id, i);
        }
        else if(this.id == 9){
          this.deleteReducerOutputShaft(i);
          this.logger.log('deleteReducerOutputShaft', this.id, i);
        }
        else if(this.id == 10){
          this.deleteReducerMounting(i);
          this.logger.log('deleteReducerMounting', this.id, i);
        }
        else if(this.id == 11){
          this.deleteReducerInstallationType(i);
          this.logger.log('deleteReducerInstallationType', this.id, i);
        }
        else if(this.id == 12){
          this.deleteReducerInputType(i);
          this.logger.log('deleteReducerInputType', this.id, i);
        }
        else if(this.id == 13){
          this.deleteManager(i);
          this.logger.log('deleteManager', this.id, i);
        }
        else if(this.id == 14){
          this.deletePaymentTerm(i);
          this.logger.log('deletePaymentTerm', this.id, i);
        }
        else if(this.id == 15){
          this.deleteEmployee(i);
          this.logger.log('deleteEmployee', this.id, i);
        }
      } else{
        this.logger.log('Пользователь выбрал не удалять');
      }

    });
  }

  downloadImage(id: number, filename: string){
    this.imageService.downloadImageById(id,filename);
  }

  deleteImage(product: Product){
    product.imageString = null;
    product.imageEmpty = true;
    product.imageChanged = true;
  }

  getHttp() {
    return this.http
  }



  addMotor() {
    var motor = new Motor()
    this.motor_list.push(motor)
  }

  saveMotor(motor: Motor) {
    motor.save(this.http).subscribe((data:any) => {
      const index = this.motor_list.findIndex(item => item.id === data.data.idMotor);
      if(index === -1 && this.motor_list[this.motor_list.length -1].id === 0){
        this.motor_list[this.motor_list.length -1].id = data.data.idMotor;
      }
    });
  }

  goToDynamicAdd(){
    const dialogAddingNewStudent = this.dialog.open(AddProductComponent, {
      width: '0px',
      height: '0px',
    });
    dialogAddingNewStudent.afterClosed().subscribe((addproducts: Product[]) => {
        this.logger.log('dialog goToDynamicAdd', addproducts);
        if(addproducts && addproducts.length>0){
            addproducts.forEach((e: { [x: string]: any; }) => {
              var product = new Product()
              product.id = e["idProduct"]
              product.productTypeId = e["productTypeId"]
              product.name = e["name"]
              product.weight = e["weight"]
              product.price = e["price"]
              product.reducerId = e["reducerId"]
              product.motorId = e["motorId"]
              product.optionsIds = e["optionsIds"]
              product.imageEmpty = e["imageEmpty"]
              product.imageString = e["imageString"]
              product.imageChanged = e["imageChanged"]
              product.rpm = e["rpm"]
              product.torqueMoment = e["torqueMoment"]
              product.serviceFactor = e["serviceFactor"]
              product.optionsString = (product.optionsIds ?? []).join(',');
              this.product_list.push(product)
          })
        }
        this.setid(3);
        this.reducer_list = [];
        this.motor_list = [];
        this.getMotorList(0)
        this.getReducer(0)

    });
  }

  deleteMotor(i : Motor) {
    i.delete(this.http).subscribe((respones: ResponseInfo<boolean>) => {
      if(respones.data !== null) {
        this.motor_list = this.motor_list.filter(item => item.id !== i.id)
        this.getMotorList(this.page);
      }
      else{
        alert(respones.errorMsg)
      }
    })
  }

  getListPaymentTerms() {
    this.http.get('/api/v1/security/paymentTerms').subscribe({
      next: (data: any) => {
        data.data.forEach((e: { [x: string]: any; }) => {
          var manager = new PaymentTerms()
          manager.idPaymentTerms =e["idPaymentTerms"]
          manager.visibleName =e["visibleName"]
          manager.fullName =e["fullName"]
          this.paymentTerms_list.push(manager)
        })
      },
      error: error => { this.logger.log(error); }
    });
  }

  addPaymentTerm() {
    var paymentTerm = new PaymentTerms()
    this.paymentTerms_list.push(paymentTerm)
  }

  savePaymentTerm(paymentTerm: PaymentTerms){
    this.logger.log(paymentTerm);
    paymentTerm.save(this.http).subscribe((respones: ResponseInfo<PaymentTerms>) => {

      this.logger.log(respones.data);
      const index = this.paymentTerms_list.findIndex(item => item.idPaymentTerms === paymentTerm.idPaymentTerms);
      if (index !== -1) {
          let  managerData = new PaymentTerms;
          managerData = paymentTerm;
          managerData.idPaymentTerms = respones.data.idPaymentTerms;

          this.paymentTerms_list[index] = managerData;
          this.logger.log('paymentTerms_list', this.manager_list);
      } else {
          let  managerData2 = new PaymentTerms;
          managerData2 = paymentTerm;
          managerData2.idPaymentTerms = respones.data.idPaymentTerms;
          this.paymentTerms_list.push(managerData2);
          this.logger.log('datadata',respones.data);
      }
    });
  }

  deletePaymentTerm(i : PaymentTerms) {
    i.delete(this.http).subscribe((respones: ResponseInfo<boolean>) => {
      if(respones.data !== null) {
        this.paymentTerms_list = this.paymentTerms_list.filter(item => item.idPaymentTerms !== i.idPaymentTerms)
      }
      else{
        alert(respones.errorMsg)
      }
    })
  }

  addMotorType() {
    var motorType = new MotorType()
    this.motorType_list.push(motorType)
  }

  saveMotorType(motorType: MotorType) {
    motorType.save(this.http).subscribe((data:any) => {
      const index = this.motorType_list.findIndex(item => item.id === data.data.idMotorType);
      if(index === -1 && this.motorType_list[this.motorType_list.length -1].id === 0){
        this.motorType_list[this.motorType_list.length -1].id = data.data.idMotorType;
      }
    });
  }

  deleteMotorType(i : MotorType) {
    i.delete(this.http).subscribe((data:boolean) => {
      if(data) {
        this.motorType_list = this.motorType_list.filter(item => item.id !== i.id)
      }
    })
  }

  addMotorAdapterType() {
    var motorAdapterType = new MotorAdapterType()
    this.motorAdapterType_list.push(motorAdapterType)
  }

  saveMotorAdapterType(motorAdapterType: MotorAdapterType) {
    motorAdapterType.save(this.http).subscribe((data:any) => {
      const index = this.motorAdapterType_list.findIndex(item => item.id === data.data.idMotorAdapterType);
      if(index === -1 && this.motorAdapterType_list[this.motorAdapterType_list.length -1].id === 0){
        this.motorAdapterType_list[this.motorAdapterType_list.length -1].id = data.data.idMotorAdapterType;
      }
    });
  }

  deleteMotorAdapterType(i : MotorAdapterType) {
    i.delete(this.http).subscribe((data:boolean) => {
      if(data) {
        this.motorAdapterType_list = this.motorAdapterType_list.filter(item => item.id !== i.id)
        this.getMotorAdapterTypeList(this.page2);
      }
    })
  }

  addProduct() {
    var product = new Product()
    product.imageEmpty = true;
    this.product_list.push(product)
  }

  resetToProductList() {
    this.isSearchMode = false;
    this.firstProductList = 0;
    this.getProductList(0);
  }


  searchProductByName(offset: number = 0) {
    if (!this.searchData || this.searchData.trim() === '') {
      this.resetToProductList();
      return;
    }
    this.isSearchMode = true;
    this.firstSearchList = 0;
    this.imageService.getPagesearchProduct(offset, this.searchData).subscribe((respones: ResponseInfo<Page<Product>>) => {
      if (respones.data !== null) {
        this.totalCountSearchList = respones.data.totalCount;
        this.product_list = respones.data.content.map((e: any) => {
          const product = new Product();
          product.id = e.idProduct;
          product.productTypeId = e.productTypeId;
          product.name = e.name;
          product.weight = e.weight;
          product.price = e.price;
          product.reducerId = e.reducerId;
          product.motorId = e.motorId;
          product.optionsIds = e.optionsIds;
          product.imageEmpty = e.imageEmpty;
          product.imageString = e.imageString;
          product.imageChanged = e.imageChanged;
          product.rpm = e.rpm;
          product.torqueMoment = e.torqueMoment;
          product.serviceFactor = e.serviceFactor;
          product.optionsString = (product.optionsIds ?? []).join(',');
          return product;
        });
      } else {
        alert(JSON.stringify(respones.errorMsg));
      }
    });
  }

  saveProduct(product: Product) {
    if(product.optionsString == null){
      product.optionsIds = null;
    }
    if(product.imageChanged == null || !product.imageChanged){
      product.imageChanged = false;
    }
    if(product.imageString == null || !product.imageString){
      product.imageString = null;
    }
    product.save(this.http).subscribe((data:any) => {
      const index = this.product_list.findIndex(item => item.id === data.data.idProduct);
      if (index !== -1) {
        this.product_list[index].optionsString = data.data.optionsIds.join(",");
      } else if (this.product_list[this.product_list.length -1].id === 0 || !this.product_list[this.product_list.length -1].id || this.product_list[this.product_list.length -1].id == null){
        this.product_list[this.product_list.length -1].id = data.data.idProduct;
        this.product_list[this.product_list.length -1].optionsString = data.data.optionsIds.join(",");
      }
    });
  }

  deleteProduct(i : Product) {
    i.delete(this.http).subscribe((data:boolean) => {
      if(data) {
        this.product_list = this.product_list.filter(item => item.id !== i.id);
        this.getProductList(this.page3);
      }
    })
  }

  getProductType() {
    this.productType_list = ProductType.getAll(this.http)
  }

  saveProductType(productType: ProductType) {
    productType.save(this.http).subscribe((data) => {
      const index = this.productType_list.findIndex(item => item.id === data.data.idProductType);
      if(index === -1 && this.productType_list[this.productType_list.length -1].id === 0){
        this.productType_list[this.productType_list.length -1].id = data.data.idProductType;
      }
    });
  }

  addProductType() {
    var productType = new ProductType()
    this.productType_list.push(productType)
  }

  deleteProductType(i: ProductType) {
    i.delete(this.http).subscribe((data:boolean) => {
      if(data) {
        this.productType_list = this.productType_list.filter(item => item.id !== i.id)
      }
    })
  }

  getProductOption(offset: number) {
    this.productService.getPageProductOptions(offset).subscribe((respones: ResponseInfo<Page<any>>)=>{
      if(respones.data !== null){
        //this.totalCount = respones.data.totalCount;
        this.totalCountProductOption = respones.data.totalCount;
        this.productOption_list = respones.data.content.map((e: any) => {
          const productOption = new ProductOption();
          productOption.idProductOption = e.idProductOption;
          productOption.productOptionValue = e.productOptionValue;
          productOption.productTypeId = e.productTypeId;
          return productOption;
        });
      } else {
        alert(JSON.stringify(respones.errorMsg))
      }
    });

  }

  saveProductOptions(i: ProductOption) {
    i.save(this.http).subscribe((data:any) => {
      const index = this.productOption_list.findIndex(item => item.idProductOption === data.data.idProductOption);
      if(index === -1 && this.productOption_list[this.productOption_list.length -1].idProductOption === 0){
        this.productOption_list[this.productOption_list.length -1].idProductOption = data.data.idProductOption
      }
    });
  }

  addProductOption() {
    var productOption = new ProductOption()
    this.productOption_list.push(productOption)
  }

  deleteProductOption(i: ProductOption) {
    i.delete(this.http).subscribe((data:boolean) => {
      if(data) {
        this.productOption_list = this.productOption_list.filter(item => item.idProductOption !== i.idProductOption);
        this.getProductOption(this.page5);
      }
    })
  }

  getReducer(offset: number) {
    this.reducerService.getPageReducers(offset).subscribe((respones: ResponseInfo<Page<any>>)=>{
      if(respones.data !== null){
        //this.totalCount = respones.data.totalCount;
        this.totalCountReducer = respones.data.totalCount;
        this.reducer_list = respones.data.content.map((e: any) => {
          const reducer = new Reducer();
          reducer.idReducer = e.idReducer;
          reducer.reducerTypeId = e.reducerTypeId;
          reducer.reducerSizeId = e.reducerSizeId;
          reducer.reducerInputTypeId = e.reducerInputTypeId;
          reducer.reducerOutputShaftTypeId = e.reducerOutputShaftTypeId;
          reducer.reducerInstallationTypeId = e.reducerInstallationTypeId;
          reducer.reducerMountingId = e.reducerMountingId;
          reducer.diameterOutputShaft = e.diameterOutputShaft;
          reducer.ratio = e.ratio;
          return reducer;
        });
      } else {
        alert(JSON.stringify(respones.errorMsg))
      }
    });
  }

  saveReducer(i: Reducer) {
    i.save(this.http).subscribe((data:any) => {
     const index = this.reducer_list.findIndex(item => item.idReducer === data.data.idReducer);
     if(index === -1 && this.reducer_list[this.reducer_list.length -1].idReducer === 0){
        this.reducer_list[this.reducer_list.length -1].idReducer = data.data.idReducer;
      }
    });
  }

  addReducer() {
    var reducer = new Reducer()
    this.reducer_list.push(reducer)
  }


  deleteReducer(i : Reducer) {
    i.delete(this.http).subscribe((respones: ResponseInfo<boolean>) => {
      if(respones.data !== null) {
        this.reducer_list = this.reducer_list.filter(item => item.idReducer !== i.idReducer);
        this.getReducer(0);
      }
      else{
        alert(respones.errorMsg)
      }
    })
  }

  getReducerType() {
    this.reducerType_list = ReducerType.getAll(this.http)
  }

  saveReducerType(i: ReducerType) {
    i.save(this.http).subscribe((data:any) => {
      const index = this.reducerType_list.findIndex(item => item.idReducerType === data.data.idReducerType);
      if(index === -1 && this.reducerType_list[this.reducerType_list.length -1].idReducerType === 0){
        this.reducerType_list[this.reducerType_list.length -1].idReducerType = data.data.idReducerType;
      }
    })
  }

  addReducerType() {
    var reducerType = new ReducerType()
    this.reducerType_list.push(reducerType)
  }

  deleteReducerType(i: ReducerType) {
    i.delete(this.http).subscribe((data:boolean) => {
      if(data) {
        this.reducerType_list = this.reducerType_list.filter(item => item.idReducerType !== i.idReducerType);
      }
    })
  }

  getReducerSize(offset: number) {
    this.reducerService.getPageReducerSizes(offset).subscribe((respones: ResponseInfo<Page<any>>)=>{
      if(respones.data !== null){
        //this.totalCount = respones.data.totalCount;
        this.totalCountReducerSize = respones.data.totalCount;
        this.reducerSize_list = respones.data.content.map((e: any) => {
          const reducerSize = new ReducerSize();
          reducerSize.idReducerSize = e.idReducerSize
          reducerSize.reducerSizeValue = e.reducerSizeValue
          reducerSize.reducerTypeId = e.reducerTypeId
          return reducerSize;
        });
      } else {
        alert(JSON.stringify(respones.errorMsg))
      }
    });
  }

  saveReducerSize(i: ReducerSize) {
    i.save(this.http).subscribe((data:any) => {
      const index = this.reducerSize_list.findIndex(item => item.idReducerSize === data.data.idReducerSize);
      if(index === -1 && this.reducerSize_list[this.reducerSize_list.length -1].idReducerSize === 0){
        this.reducerSize_list[this.reducerSize_list.length -1].idReducerSize = data.data.idReducerSize;
      }
    })
  }

  addReducerSize() {
    var reducerSize = new ReducerSize()
    this.reducerSize_list.push(reducerSize)
  }

  deleteReducerSize(i: ReducerSize) {
    i.delete(this.http).subscribe((data:boolean) => {
      if(data) {
        this.reducerSize_list = this.reducerSize_list.filter(item => item.idReducerSize !== i.idReducerSize);
        this.getReducerSize(0);
      }
    })
  }

  getReducerOutputShaft(offset: number) {
    this.reducerService.getPageReducerOutputShaftTypes(offset).subscribe((respones: ResponseInfo<Page<any>>)=>{
      if(respones.data !== null){
        //this.totalCount = respones.data.totalCount;
        this.totalCountReducerOutputShaft = respones.data.totalCount;
        this.reducerOutput_list = respones.data.content.map((e: any) => {
          const reducerOutputShaftType = new ReducerOutputShaftType();
          reducerOutputShaftType.idReducerOutputShaftType = e.idReducerOutputShaftType;
          reducerOutputShaftType.value = e.reducerOutputShaftTypeValue;
          reducerOutputShaftType.reducerTypeId = e.reducerTypeId;
          return reducerOutputShaftType;
        });
      } else {
        alert(JSON.stringify(respones.errorMsg))
      }
    });
  }

  saveReducerOutputShaft(i: ReducerOutputShaftType) {
    i.save(this.http).subscribe((data:any) => {
      const index = this.reducerOutput_list.findIndex(item => item.idReducerOutputShaftType === data.data.idReducerOutputShaftType);
      if(index === -1 && this.reducerOutput_list[this.reducerOutput_list.length -1].idReducerOutputShaftType === 0){
        this.reducerOutput_list[this.reducerOutput_list.length -1].idReducerOutputShaftType = data.data.idReducerOutputShaftType;
      }
    });
  }

  addReducerOutputShaft() {
    var reducerOutputShaftType = new ReducerOutputShaftType()
    this.reducerOutput_list.push(reducerOutputShaftType)
  }

  deleteReducerOutputShaft(i: ReducerOutputShaftType) {
    i.delete(this.http).subscribe((data:boolean) => {
      if(data) {
        this.reducerOutput_list = this.reducerOutput_list.filter(item => item.idReducerOutputShaftType !== i.idReducerOutputShaftType);
        this.getReducerOutputShaft(0);
      }
    })
  }

  getReducerMounting() {
    this.reducerMounting_list = MountingPoint.getAll(this.http)
  }

  saveReducerMounting(i: MountingPoint) {
    i.save(this.http).subscribe((data:any) => {
      const index = this.reducerMounting_list.findIndex(item => item.idReducerMounting === data.data.idReducerMounting);
      if(index === -1 && this.reducerMounting_list[this.reducerMounting_list.length -1].idReducerMounting === 0){
        this.reducerMounting_list[this.reducerMounting_list.length -1].idReducerMounting = data.data.idReducerMounting;
      }
    });
  }

  addReducerMounting() {
    var mountingPoint = new MountingPoint()
    this.reducerMounting_list.push(mountingPoint)
  }

  deleteReducerMounting(i: MountingPoint) {
    i.delete(this.http).subscribe((data:boolean) => {
      if(data) {
        this.reducerMounting_list = this.reducerMounting_list.filter(item => item.idReducerMounting !== i.idReducerMounting)
      }
    })
  }

  getReducerInstallationType(offset:number) {
    this.reducerService.getPageReducerInstallationType(offset).subscribe((respones: ResponseInfo<Page<any>>)=>{
      if(respones.data !== null){
        //this.totalCount = respones.data.totalCount;
        this.totalCountReducerInstallationType = respones.data.totalCount;
        this.reducerInstallationType_list = respones.data.content.map((e: any) => {
          const reducerInstallationType = new ReducerInstallationType();
          reducerInstallationType.idReducerInstallationType = e.idReducerInstallationType;
          reducerInstallationType.value = e.reducerInstallationTypeValue;
          reducerInstallationType.reducerTypeId = e.reducerTypeId;
          return reducerInstallationType;
        });
      } else {
        alert(JSON.stringify(respones.errorMsg))
      }
    });

  }

  saveReducerInstallationType(i: ReducerInstallationType) {
    i.save(this.http).subscribe((data:any) => {
      const index = this.reducerInstallationType_list.findIndex(item => item.idReducerInstallationType === data.data.idReducerInstallationType);
      if(index === -1 && this.reducerInstallationType_list[this.reducerInstallationType_list.length -1].idReducerInstallationType === 0){
        this.reducerInstallationType_list[this.reducerInstallationType_list.length -1].idReducerInstallationType = data.data.idReducerInstallationType;
      }
    });
  }

  addReducerInstallationType() {
    var reducerInstallationType = new ReducerInstallationType()
    this.reducerInstallationType_list.push(reducerInstallationType)
  }

  deleteReducerInstallationType(i: ReducerInstallationType) {
    i.delete(this.http).subscribe((data:boolean) => {
      if(data) {
        this.reducerInstallationType_list = this.reducerInstallationType_list.filter(item => item.idReducerInstallationType !== i.idReducerInstallationType);
        this.getReducerInstallationType(0);
      }
    })
  }

  getReducerInputType(offset: number) {
    this.reducerService.getPageReducerInputType(offset).subscribe((respones: ResponseInfo<Page<any>>)=>{
      if(respones.data !== null){
        //this.totalCount = respones.data.totalCount;
        this.totalCountReducerInputType = respones.data.totalCount;
        this.reducerInputType_list = respones.data.content.map((e: any) => {
          const reducerInputType = new ReducerInputType();
          reducerInputType.idReducerInputType = e.idReducerInputType;
          reducerInputType.value = e.reducerInputTypeValue;
          reducerInputType.reducerTypeId = e.reducerTypeId;
          return reducerInputType;
        });
      } else {
        alert(JSON.stringify(respones.errorMsg))
      }
    });

  }

  saveReducerInputType(i: ReducerInputType){
    i.save(this.http).subscribe((data:any) => {
      const index = this.reducerInputType_list.findIndex(item => item.idReducerInputType === data.data.idReducerInputType);
      if(index === -1 && this.reducerInputType_list[this.reducerInputType_list.length -1].idReducerInputType === 0){
        this.reducerInputType_list[this.reducerInputType_list.length -1].idReducerInputType = data.data.idReducerInputType;
      }
    });
  }

  addReducerInputType() {
    var reducerInputType = new ReducerInputType()
    this.reducerInputType_list.push(reducerInputType)
  }

  deleteReducerInputType(i: ReducerInputType) {
    i.delete(this.http).subscribe((data:boolean) => {
      if(data) {
        this.reducerInputType_list = this.reducerInputType_list.filter(item => item.idReducerInputType !== i.idReducerInputType);
        this.getReducerInputType(0);
      }
    })
  }

  getMotorList(offset: number) {
    this.motorService.getPageMotor(offset).subscribe((respones: ResponseInfo<Page<Motor>>)=>{
      if(respones.data !== null){
        //this.totalCount = respones.data.totalCount;
        this.totalCountMotorList = respones.data.totalCount;
        this.motor_list = respones.data.content.map((e: any) => {
          const motor = new Motor();
          motor.id = e.idMotor;
          // motor.frequency.value = e.frequency;
          motor.polesNumber = e.polesNumber;
          motor.adapterType.id = e.motorAdapterTypeId;
          motor.power = e.power;
          motor.efficiency = e.efficiency;
          motor.ratedCurrent = e.ratedCurrent;
          motor.momentOfInertia = e.momentOfInertia;
          motor.type.id = e.motorTypeId;
          return motor;
        });
      } else {
        alert(JSON.stringify(respones.errorMsg))
      }
    });

}

  getProductList(offset: number) {
    this.productService.getPageProducts(offset).subscribe((respones: ResponseInfo<Page<any>>) => {
      if (respones.data !== null) {
        this.totalCountProductList = respones.data.totalCount;
        this.product_list = respones.data.content.map((e: any) => {
          const product = new Product();
          product.id = e.idProduct;
          product.productTypeId = e.productTypeId;
          product.name = e.name;
          product.weight = e.weight;
          product.price = e.price;
          product.reducerId = e.reducerId;
          product.motorId = e.motorId;
          product.optionsIds = e.optionsIds;
          product.imageEmpty = e.imageEmpty;
          product.imageString = e.imageString;
          product.imageChanged = e.imageChanged;
          product.rpm = e.rpm;
          product.torqueMoment = e.torqueMoment;
          product.serviceFactor = e.serviceFactor;
          product.optionsString = (product.optionsIds ?? []).join(',');
          return product;
        });
      } else {
        alert(JSON.stringify(respones.errorMsg));
      }
    });
  }

  getMotorTypeList() {
    this.http.get('/api/v1/motorType').subscribe({
      next: (data: any) => {
        data.data.forEach((e: { [x: string]: any; }) => {
          var motorType = new MotorType()
          motorType.id = e["idMotorType"]
          motorType.value = e["motorTypeName"]
          this.motorType_list.push(motorType)
        })
      },
      error: error => { this.logger.log(error); }
    });
  }

  getMotorAdapterTypeList(offset: number) {
    this.motorService.getPageMotorAdapterType(offset).subscribe((respones: ResponseInfo<Page<MotorAdapterType>>)=>{
      if(respones.data !== null){
        //this.totalCount = respones.data.totalCount;
        this.totalCountMotorAdapterTypeList = respones.data.totalCount;
        this.motorAdapterType_list = respones.data.content.map((e: any) => {
          const motorAdapter = new MotorAdapterType();
          motorAdapter.id = e.idMotorAdapterType;
          motorAdapter.value = e.motorAdapterTypeValue;
          motorAdapter.typeid = e.motorTypeId;
          return motorAdapter;
        });
      } else {
        alert(JSON.stringify(respones.errorMsg))
      }
    });
  }

  getListEmployees(offset: number) {
    this.employeeService.getPageEmployees(offset).subscribe((respones: ResponseInfo<Page<any>>)=>{
      if(respones.data !== null){
        //this.totalCount = respones.data.totalCount;
        this.totalCountListEmployees = respones.data.totalCount;
        this.employee_list = respones.data.content.map((e: any) => {
          const employee = new Employee();
          employee.idEmployee =e.idEmployee;
          employee.login =e.login;
          employee.password =e.password;
          employee.admin =e.admin;
          return employee;
        });
      } else {
        alert(JSON.stringify(respones.errorMsg))
      }
    });
  }

  deleteEmployee(i: Employee) {
    i.delete(this.http).subscribe((data:boolean) => {
      if(data) {
        this.employee_list = this.employee_list.filter(item => item.idEmployee !== i.idEmployee);
        this.getListEmployees(0);
      }
    })
  }

  saveEmployee(employee: Employee){
    this.logger.log(employee);
    employee.save(this.http).subscribe((respones: ResponseInfo<Employee>) => {

      this.logger.log(respones.data);
      const index = this.employee_list.findIndex(item => item.idEmployee === employee.idEmployee);
      if (index !== -1) {
          let  employeeData = new Employee;
          employeeData = employee;
          employeeData.idEmployee = respones.data.idEmployee;

          this.employee_list[index] = employeeData;
          this.logger.log('employee_list', this.employee_list);
      } else {
          let  employeeData2 = new Employee;
          employeeData2 = employee;
          employeeData2.idEmployee = respones.data.idEmployee;
          this.employee_list.push(employeeData2);
          this.logger.log('datadata',respones.data);
      }
    });
  }

  addEmployee(){
    var newEmployee = new Employee()
    this.employee_list.push(newEmployee)
  }

  addManager(){
    var newManager = new Manager()
    this.manager_list.push(newManager)
  }

  getListManagers(offset: number) {
    this.managerService.getPageManagers(offset).subscribe((respones: ResponseInfo<Page<any>>)=>{
      if(respones.data !== null){
        //this.totalCount = respones.data.totalCount;
        this.totalCountListManagers = respones.data.totalCount;
        this.manager_list = respones.data.content.map((e: any) => {
          const manager = new Manager();
          manager.idManager =e.idManager;
          manager.shortName =e.shortName;
          manager.fullName =e.fullName;
          manager.position =e.position;
          manager.email =e.email;
          manager.phoneNumber =e.phoneNumber;
          manager.idUser = e.idUser;
          return manager;
        });
      } else {
        alert(JSON.stringify(respones.errorMsg))
      }
    });
  }

  deleteManager(i: Manager) {
    i.delete(this.http).subscribe((data:boolean) => {
      if(data) {
        this.manager_list = this.manager_list.filter(item => item.idManager !== i.idManager);
        this.getListManagers(0);
      }
    })
  }

  saveManager(manager: Manager){
    this.logger.log(manager);
    manager.save(this.http).subscribe((respones: ResponseInfo<Manager>) => {

      this.logger.log(respones.data);
      const index = this.manager_list.findIndex(item => item.idManager === manager.idManager);
      if (index !== -1) {
          let  managerData = new Manager;
          managerData = manager;
          managerData.idManager = respones.data.idManager;

          this.manager_list[index] = managerData;
          this.logger.log('manager_list', this.manager_list);
      } else {
          let  managerData2 = new Manager;
          managerData2 = manager;
          managerData2.idManager = respones.data.idManager;
          this.manager_list.push(managerData2);
          this.logger.log('datadata',respones.data);
      }
    });
  }

}
