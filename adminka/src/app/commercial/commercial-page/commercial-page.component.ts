import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { CommercialService } from 'src/app/services/commercial.service';
import { DataService } from 'src/app/services/data.service';
import { SearchPageComponent } from '../search-page/search-page.component';
import { DeletecommComponent } from '../deletecomm/deletecomm.component';
import { CommercialProp } from 'src/app/models/commercialProp';
import { Manager } from 'src/app/models/manager';
import { ResponseInfo } from 'src/app/models/responesInfo';
import { Page } from 'src/app/models/page';

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
  totalCount: number;

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
    const dialogAddingNewStudent = this.dialog.open(DeletecommComponent, {
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

  // getAllcommercialProp() {
  //   this.commercialService.getAllCommercialProps().subscribe((respones: ResponseInfo<CommercialProp[]>) => {
  //     if(respones.data !== null){
  //       console.log("Data getAllcommercialProp: ", respones.data);
  //       this.commercialProp_list = respones.data;
  //     } else {
  //       alert(JSON.stringify(respones.errorMsg))
  //     }
  //   });
  // }


  getAllcommercialProp() {
    const defaultObj = new CommercialProp ();
    this.commercialService.filterPageProp(defaultObj,0).subscribe((respones: ResponseInfo<Page<CommercialProp>>) => {
      if(respones.data !== null){
        console.log("Data getAllcommercialProp: ", respones.data);
        this.commercialProp_list = respones.data.content;
        this.totalCount = respones.data.totalCount;
      } else {
        alert(JSON.stringify(respones.errorMsg))
      }
    });
  }

  downloadExcel(comm:CommercialProp){
    this.commercialService.downloadExcelById(comm);
  }

  preventNegative(event: KeyboardEvent): void {
    if (event.key === '-' || event.key === 'e') {
      event.preventDefault();
    }
  }

  downloadPdf(comm:CommercialProp){
    this.commercialService.downloadPdfById(comm);
  }

  onPageChange(event: any){
    console.log("event.page", event.page);
    this.searchProp(event.page);
  }

  searchProp(offset: number){
    // this.searchManager.shortName = this.shortName;
    if(this.searchManager.shortName == null){
      this.searchData.manager = null;
    } else {
      this.searchData.manager = this.searchManager;
    }
    console.log('searchData', this.searchData)
    // this.commercialService.filterProp(this.searchData).subscribe((respones: ResponseInfo<CommercialProp[]>) => {
    this.commercialService.filterPageProp(this.searchData,offset).subscribe((respones: ResponseInfo<Page<CommercialProp>>) => {
      if(respones.data !== null){
        console.log("Result searchProp: ", respones.data);
        this.commercialProp_list = respones.data.content;
        this.totalCount = respones.data.totalCount;
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
  // onMarginRatioChange(value: number) {
  //   this.searchData.marginRatio = value ? value : null;
  // }

  onTimestampChange(value: string) {
    this.searchData.timestamp = value ? value : null;
  }

  onNumberChange(value: string) {
    this.searchData.number = value ? value : null;
  }

}
