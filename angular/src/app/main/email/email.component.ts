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
 sendData: boolean | null = null;
 email: Email;

//  form: FormGroup;

 selectEmail: string;
 selecetNumber: string;
 selectName: string;

  constructor( private logger: NGXLogger, public dialogRef: MatDialogRef<EmailComponent>, @Inject(MAT_DIALOG_DATA) public data:string, private productService: ProductService,){
      this.nameProduct = data;
      this.email = new Email;
  }


  // ngOnInit() {
  //   this.form = new FormGroup({
  //     phone: new FormControl('')
  //   })

  //   let phoneControl = this.form.controls['phone'];

  //   phoneControl.valueChanges.subscribe(() => {
  //     if (phoneControl.value === "") {
  //       phoneControl.setValidators(null);
  //     } else {
  //       phoneControl.setValidators(this.phoneValidator());
  //     }
  //     phoneControl.updateValueAndValidity({emitEvent: false});
  //   });


  // }


  send(){
    this.email.email = this. selectEmail;
    this.email.name = this.selectName;
    this.email.phoneNumber = this.selecetNumber;
    this.email.productName = this.nameProduct;
    this.productService.sendEmail(this.email).subscribe((respones: ResponseInfo<boolean>)=>{
      this.logger.log('EmailData', this.email );
      if(respones.data !== null){
        this.logger.log('result SendEmail', respones.data);
        // this.dialogRef.close(respones.data);
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


  // isValidPhoneNumber(phoneNumber: string) {
  //   return new RegExp(/^[ 0 ]{1,1}?[0-9- ]{9,15}$/).test(phoneNumber);
  // }

  // onSubmit() {
  //   this.logger.log('valid', this.form.valid);
  // }



  // phoneValidator(): ValidatorFn {
  //   return (control): ValidationErrors => {
  //     if (!this.isValidPhoneNumber(control.value)) {
  //       return {
  //         'invalidPhone': true
  //       }
  //     } else {
  //       return {
  //         'invalidPhone': false
  //       }
  //     }
  //   }
  // }

}
