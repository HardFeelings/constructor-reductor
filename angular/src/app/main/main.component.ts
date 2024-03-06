import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from "@angular/router";

@Component({
  selector: 'app-main',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './main.component.html',
  styleUrl: './main.component.scss'
})
export class MainComponent {
  items1: Array<any> = []
  items2: Array<any> = []
  items3: Array<any> = []
  items4: Array<any> = []
  reducerType = ""
  test: string = ""

  constructor(private http: HttpClient) {

  }


  send() {
    this.http.get(`/api/v1/gearboxVersion/byReducerTypeId/${this.reducerType}`).subscribe({
      next: (data: any) => {
        console.log(data.data)
        this.items3 = data.data
      },
      error: error => { console.log(error); }
    });
    this.http.get(`/api/v1/shaftVersion/byReducerTypeId/${this.reducerType}`).subscribe({
      next: (data: any) => {
        console.log(data.data)
        this.items4 = data.data
      },
      error: error => { console.log(error); }
    });
  }

  ngOnInit() {
    this.http.get('/api/v1/reducerType').subscribe({
      next: (data: any) => {
        console.log(data.data)
        this.items1 = data.data
      },
      error: error => { console.log(error); }
    });
    this.http.get('/api/v1/inputNode').subscribe({
      next: (data: any) => {
        console.log(data.data)
        this.items2 = data.data
      },
      error: error => { console.log(error); }
    });
  }
}