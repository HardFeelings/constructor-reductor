import { Component,Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-delete',
  templateUrl: './delete.component.html',
  styleUrls: ['./delete.component.scss']
})
export class DeleteComponent {
  deleteNumber: string | null;

  constructor(public dialogRef: MatDialogRef<DeleteComponent>,
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
