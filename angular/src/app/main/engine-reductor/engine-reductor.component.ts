import { Motor } from './../../classes/motor';
import { Reducer } from './../../classes/reducer';
import { Component } from '@angular/core';
import { MotorReducer } from 'src/app/classes/motor-reducer';

@Component({
  selector: 'app-engine-reductor',
  templateUrl: './engine-reductor.component.html',
  styleUrls: ['./engine-reductor.component.scss']
})
export class EngineReductorComponent {
  motorReducer:MotorReducer;
  reducer:Reducer;
  motor:Motor;

  constructor(){
    this.motorReducer = new MotorReducer();
    this.motor = new Motor();
    this.reducer = new Reducer();
  }
}
