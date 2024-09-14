import { Component,Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
@Component({
  selector: 'app-deletecomm',
  templateUrl: './deletecomm.component.html',
  styleUrls: ['./deletecomm.component.scss']
})
export class DeletecommComponent {
  deleteNumber: string | null;

  constructor(public dialogRef: MatDialogRef<DeletecommComponent>,
    @Inject(MAT_DIALOG_DATA) public data:string){
      this.deleteNumber = this.data;
      console.log('this.deleteNumber',this.deleteNumber)
  }

  close(){
    this.dialogRef.close(false);
  }
  deleteProp(){
    this.dialogRef.close(true);
  }
}
