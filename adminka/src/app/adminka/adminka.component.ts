import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Motor, MotorAdapterType, MotorType } from '../classes/motor';
import { FormsModule } from '@angular/forms';
import { Product, ProductOption, ProductType } from '../classes/product';
import { MountingPoint, Reducer, ReducerAdapterType, ReducerInputType, ReducerInstallationType, ReducerOutputShaftType, ReducerSize, ReducerType } from '../classes/reducer';
import { ImageService } from '../services/image.service';
import { Manager } from '../classes/manager';
import { ResponseInfo } from '../classes/responesInfo';
import { AddProductComponent } from './add-product/add-product.component';
import { MatDialog } from '@angular/material/dialog';


@Component({
  selector: 'app-adminka',
  templateUrl: './adminka.component.html',
  styleUrls: ['./adminka.component.scss']
})
export class AdminkaComponent {
  id = 0
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
  reducerAdapterType_list: ReducerAdapterType[]
  manager_list:Manager[];

  constructor(private http: HttpClient, private imageService: ImageService, public dialog:MatDialog) {
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
    this.reducerAdapterType_list = new Array<ReducerAdapterType>();
    this.manager_list = new Array<Manager>();
  }

  setid(id: number) {
    this.id = id;
    const spans = document.querySelectorAll('.sidebar span');
    spans.forEach((span, index) => {
        if (index === id) {
            span.classList.add('selected');
        } else {
            span.classList.remove('selected');
        }
    });
  }

  onFileSelected(event: any, product: Product) {
    const selectedFile = event.target.files[0];
    const reader = new FileReader();

    reader.onload = (e: any) => {
      const base64Image = e.target.result;
      const base64WithoutPrefix = base64Image.split(',')[1];
      console.log('base64:', base64WithoutPrefix);

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

  ngOnInit() {
    this.getMotorList()
    this.getMotorTypeList()
    this.getMotorAdapterTypeList()
    this.getProductList()
    this.getProductType()
    this.getProductOption()
    this.getReducer()
    this.getReducerSize()
    this.getReducerType()
    this.getReducerOutputShaft()
    this.getReducerMounting()
    this.getReducerInstallationType()
    this.getReducerInputType()
    this.getReducerAdapterType()
    this.getListManagers()
    this.setid(0);
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
      width: '3000px',
      height: '1300px',
      // data:
    });
    dialogAddingNewStudent.afterClosed().subscribe((addproducts: Product[]) => {
        console.log('dialog goToDynamicAdd', addproducts);
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
        this.getMotorList()
        this.getReducer()

    });
  }

  deleteMotor(i : Motor) {
    i.delete(this.http).subscribe((data:boolean) => {
      if(data) {
        this.motor_list = this.motor_list.filter(item => item.id !== i.id)
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
      }
    })
  }

  addProduct() {
    var product = new Product()
    product.imageEmpty = true;
    this.product_list.push(product)
  }

  saveProduct(product: Product) {
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
        this.product_list = this.product_list.filter(item => item.id !== i.id)
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

  getProductOption() {
    this.productOption_list = ProductOption.getAll(this.http)
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
        this.productOption_list = this.productOption_list.filter(item => item.idProductOption !== i.idProductOption)
      }
    })
  }

  getReducer() {
    this.reducer_list = Reducer.getAll(this.http)
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

  deleteReducer(i: Reducer) {
    i.delete(this.http).subscribe((data:boolean) => {
      if(data) {
        this.reducer_list = this.reducer_list.filter(item => item.idReducer !== i.idReducer)
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
        this.reducerType_list = this.reducerType_list.filter(item => item.idReducerType !== i.idReducerType)
      }
    })
  }

  getReducerSize() {
    this.reducerSize_list = ReducerSize.getAll(this.http)
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
        this.reducerSize_list = this.reducerSize_list.filter(item => item.idReducerSize !== i.idReducerSize)
      }
    })
  }

  getReducerOutputShaft() {
    this.reducerOutput_list = ReducerOutputShaftType.getAll(this.http)
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
        this.reducerOutput_list = this.reducerOutput_list.filter(item => item.idReducerOutputShaftType !== i.idReducerOutputShaftType)
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

  getReducerInstallationType() {
    this.reducerInstallationType_list = ReducerInstallationType.getAll(this.http)
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
        this.reducerInstallationType_list = this.reducerInstallationType_list.filter(item => item.idReducerInstallationType !== i.idReducerInstallationType)
      }
    })
  }

  getReducerInputType() {
    this.reducerInputType_list = ReducerInputType.getAll(this.http)
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
        this.reducerInputType_list = this.reducerInputType_list.filter(item => item.idReducerInputType !== i.idReducerInputType)
      }
    })
  }

  getReducerAdapterType() {
    this.reducerAdapterType_list = ReducerAdapterType.getAll(this.http)
  }

  saveReducerIAdapterType(i: ReducerAdapterType) {
    i.save(this.http).subscribe((data:any) => {
      const index = this.reducerAdapterType_list.findIndex(item => item.idReducerAdapterType === data.data.idReducerAdapterType);
      if(index === -1 && this.reducerAdapterType_list[this.reducerAdapterType_list.length -1].idReducerAdapterType === 0){
        this.reducerAdapterType_list[this.reducerAdapterType_list.length -1].idReducerAdapterType  = data.data.idReducerAdapterType ;
      }
    });
  }

  addReducerAdapterType() {
    var reducerAdapterType = new ReducerAdapterType()
    this.reducerAdapterType_list.push(reducerAdapterType)
  }

  deleteReducerAdapterType(i: ReducerAdapterType) {
    i.delete(this.http).subscribe((data:boolean) => {
      if(data) {
        this.reducerAdapterType_list = this.reducerAdapterType_list.filter(item => item.idReducerAdapterType !== i.idReducerAdapterType)
      }
    })
  }

  getMotorList() {
    this.http.get('/api/v1/motor').subscribe({
      next: (data: any) => {
        data.data.forEach((e: { [x: string]: any; }) => {
          var motor = new Motor()
          motor.id = e["idMotor"]
          motor.frequency.value = e["frequency"]
          motor.adapterType.id = e["motorAdapterTypeId"]
          motor.power = e["power"]
          motor.efficiency =  e["efficiency"]
          motor.ratedCurrent =  e["ratedCurrent"]
          motor.posTerminalBox =  e["posTerminalBox"]
          motor.momentOfInertia =  e["momentOfInertia"]
          motor.cableExitSide =  e["cableExitSide"]
          motor.type.id = e["motorTypeId"]
          this.motor_list.push(motor)
        })
      },
      error: error => { console.log(error); }
    });
  }

  getProductList() {
    this.http.get('/api/v1/product').subscribe({
      next: (data: any) => {
        data.data.forEach((e: { [x: string]: any; }) => {
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
      },
      error: error => { console.log(error); }
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
      error: error => { console.log(error); }
    });
  }

  getMotorAdapterTypeList() {
    this.http.get('/api/v1/motorAdapterType').subscribe({
      next: (data: any) => {
        data.data.forEach((e: { [x: string]: any; }) => {
          var motorAdapterType = new MotorAdapterType()
          motorAdapterType.id = e["idMotorAdapterType"]
          motorAdapterType.value = e["motorAdapterTypeValue"]
          motorAdapterType.typeid = e["motorTypeId"]
          this.motorAdapterType_list.push(motorAdapterType)
        })
      },
      error: error => { console.log(error); }
    });
  }

  addManager(){
    var newManager = new Manager()
    this.manager_list.push(newManager)
  }

  getListManagers() {
    this.http.get('/api/v1/security/manager').subscribe({
      next: (data: any) => {
        data.data.forEach((e: { [x: string]: any; }) => {
          var manager = new Manager()
          manager.idManager =e["idManager"]
          manager.shortName =e["shortName"]
          manager.fullName =e["fullName"]
          manager.position =e["position"]
          manager.email =e["email"]
          manager.phoneNumber =e["phoneNumber"]
          this.manager_list.push(manager)
        })
      },
      error: error => { console.log(error); }
    });
  }

  deleteManager(i: Manager) {
    i.delete(this.http).subscribe((data:boolean) => {
      if(data) {
        this.manager_list = this.manager_list.filter(item => item.idManager !== i.idManager)
      }
    })
  }

  saveManager(manager: Manager){
    console.log(manager);
    manager.save(this.http).subscribe((respones: ResponseInfo<Manager>) => {

      console.log(respones.data);
      const index = this.manager_list.findIndex(item => item.idManager === manager.idManager);
      if (index !== -1) {
          let  managerData = new Manager;
          managerData = manager;
          managerData.idManager = respones.data.idManager;

          this.manager_list[index] = managerData;
          console.log('manager_list', this.manager_list);
      } else {
          let  managerData2 = new Manager;
          managerData2 = manager;
          managerData2.idManager = respones.data.idManager;
          this.manager_list.push(managerData2);
          console.log('datadata',respones.data);
      }
    });
  }

}
