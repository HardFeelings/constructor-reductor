import { Component, Input } from '@angular/core';
import { Product, enProduct } from 'src/app/classes/product';
import { Reducer } from 'src/app/classes/reducer';

@Component({
  selector: 'app-reductor',
  templateUrl: './reductor.component.html',
  styleUrls: ['./reductor.component.scss']
})
export class ReductorComponent {
  reducer: Reducer;


   constructor(){
    this.reducer = new Reducer();
   }

   ngOninit(){
   }

}
