import { Component } from '@angular/core';
import { CommercialService } from '../sevices/commercial.service';
import { CommercialProp } from '../models/commercialProp';
import { ResponseInfo } from '../models/responesInfo';
import { DataService } from '../sevices/data.service';
import { Router } from '@angular/router';
import { SearchPageComponent } from '../search-page/search-page.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-commercial-page',
  templateUrl: './commercial-page.component.html',
  styleUrls: ['./commercial-page.component.scss']
})
export class CommercialPageComponent {
  commercialProp_list: CommercialProp[];

  constructor(private commercialService: CommercialService, private dataService: DataService, private  router: Router, public dialog:MatDialog){
  }


  ngOnInit() {
    this.getAllcommercialProp();
  }

  goToSearchPage(id:number | null){
    const dialogAddingNewStudent = this.dialog.open(SearchPageComponent, {
      width: '1500px',
      height: '800px',
      data: id
    });
    dialogAddingNewStudent.afterClosed().subscribe((commercialProp: CommercialProp) => {
      if(commercialProp  !== null && commercialProp  !== undefined) {
        console.log('dialog commercialProp', commercialProp);
        this.getAllcommercialProp();
      } else{
        console.log('Окно закрыто без изменений');
        this.getAllcommercialProp();
      }

    });
  }

  getAllcommercialProp() {
    this.commercialService.getAllCommercialProps().subscribe((respones: ResponseInfo<CommercialProp[]>) => {
      if(respones.data !== null){
        console.log("Data getAllcommercialProp: ", respones.data);
        this.commercialProp_list = respones.data;
      } else {
        alert(JSON.stringify(respones.errorMsg))
      }
    });
  }

  deleteCommercial(id: number){
    this.commercialService.deleteCommercialProp(id).subscribe((respones: ResponseInfo<Boolean>) => {
      if(respones.data !== null){
        console.log("Result deleteCommercial: ", respones.data);
        this.commercialProp_list = this.commercialProp_list.filter(item => item.idCommercialProp !== id);
      } else {
        alert(JSON.stringify(respones.errorMsg))
      }
    });
  }

  // goToSearchPage(id:number | null){
  //   this.dataService.setId(id);
  //   this.router.navigate(['/items']);
  // }

  // addNewCommercial() {
  //   var newCommercial = new CommercialProp()
  //   this.commercialProp_list.push(newCommercial)
  // }

  // saveCommercial(commercial: CommercialProp){
  //   this.commercialService.saveCommercialProp(commercial).subscribe(
  //     (respones: ResponseInfo<CommercialProp>) => {
  //       console.log("Result Data saveCommercial: ", respones.data);
  //       const index = this.commercialProp_list.findIndex(item => item.idCommercialProp === respones.data.idCommercialProp);
  //       if(index === -1 && this.commercialProp_list[this.commercialProp_list.length -1].idCommercialProp === 0){
  //         this.commercialProp_list[this.commercialProp_list.length -1].idCommercialProp = respones.data.idCommercialProp;
  //       }
  //       // this.commercialProp_list = this.commercialProp_list.filter(item => item.idCommercialProp !== id);
  //     },
  //     (exepcion: any) => {
  //       console.error("Error saveCommercial:", exepcion.error);
  //     }
  //   );

  // }
}
