import { Component, Inject } from '@angular/core';
import { FormControl, FormGroup, ValidationErrors, ValidatorFn } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Email } from 'src/app/models/email';
import { ResponseInfo } from 'src/app/models/responesInfo';
import { ProductService } from 'src/app/sevices/product.service';
import { NGXLogger } from "ngx-logger";

@Component({
  selector: 'app-email',
  templateUrl: './email.component.html',
  styleUrls: ['./email.component.scss']
})
export class EmailComponent {
 nameProduct: string;
 productObj: any;
 sendData: boolean | null = null;
 email: Email;
 selectEmail: string;
 selecetNumber: string;
 selectName: string;

  constructor( private logger: NGXLogger, public dialogRef: MatDialogRef<EmailComponent>, @Inject(MAT_DIALOG_DATA) public data: { obj: any; name: string }, private productService: ProductService,){
      this.nameProduct = data.name;
      this.productObj = data.obj;
      this.email = new Email;
  }

  send(){
    this.email.email = this. selectEmail;
    this.email.name = this.selectName;
    this.email.fields = JSON.stringify(this.productObj);
    this.email.phoneNumber = this.selecetNumber;
    this.email.productName = this.nameProduct;
    this.productService.sendEmail(this.email).subscribe((respones: ResponseInfo<boolean>)=>{
      this.logger.log('EmailData', this.email );
      if(respones.data !== null){
        this.logger.log('result SendEmail', respones.data);
        this.sendData = true;
      }
      else{
        alert(JSON.stringify(respones.errorMsg))
        this.sendData = false;
      }
    });
  }

  close(){
    this.dialogRef.close(this.sendData);
  }

}
