import { Component } from '@angular/core';
import { CommercialService } from '../sevices/commercial.service';
import { CommercialProp } from '../models/commercialProp';
import { ResponseInfo } from '../models/responesInfo';
import { DataService } from '../sevices/data.service';
import { Router } from '@angular/router';
import { SearchPageComponent } from '../search-page/search-page.component';
import { MatDialog } from '@angular/material/dialog';
import { Manager } from '../models/manager';
import { DeleteComponent } from '../delete/delete.component';

@Component({
  selector: 'app-commercial-page',
  templateUrl: './commercial-page.component.html',
  styleUrls: ['./commercial-page.component.scss']
})
export class CommercialPageComponent {
  commercialProp_list: CommercialProp[];
  searchData: CommercialProp;
  shortName: string | null = null;
  searchManager: Manager;

  constructor(private commercialService: CommercialService, private dataService: DataService, private  router: Router, public dialog:MatDialog){
    this.searchData = new CommercialProp;
    this.searchManager = new Manager;
  }


  ngOnInit() {
    this.getAllcommercialProp();
  }

  goToSearchPage(id:number | null){
    const dialogAddingNewStudent = this.dialog.open(SearchPageComponent, {
      width: '2000px',
      height: '1500px',
      data: id,
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


  oKDelete(id: number, number:string | null){
    const dialogAddingNewStudent = this.dialog.open(DeleteComponent, {
      width: '600px',
      height: '300px',
      data: number,
    });
    dialogAddingNewStudent.afterClosed().subscribe((okOrNot: boolean) => {
      if(okOrNot){
        this.commercialService.deleteCommercialProp(id).subscribe((respones: ResponseInfo<Boolean>) => {
          if(respones.data !== null){
            console.log("Result deleteCommercial: ", respones.data);
            this.commercialProp_list = this.commercialProp_list.filter(item => item.idCommercialProp !== id);
          } else {
            alert(JSON.stringify(respones.errorMsg))
          }
        });
      }
      else{
        console.log('Пользователь выбрал не удалять');
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

  // deleteCommercial(id: number){
  //   this.commercialService.deleteCommercialProp(id).subscribe((respones: ResponseInfo<Boolean>) => {
  //     if(respones.data !== null){
  //       console.log("Result deleteCommercial: ", respones.data);
  //       this.commercialProp_list = this.commercialProp_list.filter(item => item.idCommercialProp !== id);
  //     } else {
  //       alert(JSON.stringify(respones.errorMsg))
  //     }
  //   });
  // }

  downloadExcel(id:number){
    this.commercialService.downloadExcelById(id);
  }

  downloadPdf(id:number){
    this.commercialService.downloadPdfById(id);
  }

  searchProp(){
    // this.searchManager.shortName = this.shortName;
    if(this.searchManager.shortName == null){
      this.searchData.manager = null;
    } else {
      this.searchData.manager = this.searchManager;
    }
    console.log('searchData', this.searchData)
    this.commercialService.filterProp(this.searchData).subscribe((respones: ResponseInfo<CommercialProp[]>) => {
      if(respones.data !== null){
        console.log("Result searchProp: ", respones.data);
        this.commercialProp_list = respones.data;
      } else {
        alert(JSON.stringify(respones.errorMsg))
      }
    });
  }

  clearFilter(){
    this.searchManager =  new Manager ;
    this.searchData = new CommercialProp;
    this.getAllcommercialProp();
  }

  onShortNameChange(value: string) {
    this.searchManager.shortName = value ? value : null;
  }
  onPartnerChange(value: string) {
    this.searchData.partner = value ? value : null;
  }
  onMarginRatioChange(value: number) {
    this.searchData.marginRatio = value ? value : null;
  }

  onTimestampChange(value: string) {
    this.searchData.timestamp = value ? value : null;
  }

  onNumberChange(value: string) {
    this.searchData.number = value ? value : null;
  }

}
