import { Component } from '@angular/core';
import { Motor } from 'src/app/classes/motor';
import { Engine, EngineAdapterType, EngineType } from 'src/app/models/engine';
import { ResponseInfo } from 'src/app/models/responesInfo';
import { MotorService } from 'src/app/sevices/motor.service';

@Component({
  selector: 'app-engine',
  templateUrl: './engine.component.html',
  styleUrls: ['./engine.component.scss']
})
export class EngineComponent {
motor: Motor;

constructor(private motorService: MotorService){
  this.motor = new Motor();
}


ngOnInit() {
  this.getAllMotors();
  this.gerMotorbyId(1);

  this.getAllMotorType();
  // this.gerMotorTypebyId(1);

  this.getAllMotoraAdapterType();
  // this.getMotorAdapterById(1);
  // this.getMotorAdapterByMotorTypeId(1);

}


  //motor-controller and motor-type-controller

  getAllMotors() {
    this.motorService.getAllMotor().subscribe(
      (respones: ResponseInfo<Engine[]>) => {
        console.log("Data getAllMotors: ", respones.data);
      },
      (exepcion: any) => {
        console.error("Error getAllMotors:", exepcion.error);
      }
    );
  }

  gerMotorbyId(id:number) {
    this.motorService.getMotorById(id).subscribe(
      (data: ResponseInfo<Engine>)=>{
      console.log("Data gerMotorbyId", data);
      },
      (error:any) =>{
        console.error("Error gerMotorbyId:", error);
      }
    );
  }

  getAllMotorType() {
    this.motorService.getAllMotorType().subscribe(
      (respones: ResponseInfo<EngineType[]>) => {
        console.log("Data getAllMotorType: ", respones.data);
      },
      (exepcion: any) => {
        console.error("Error getAllMotorType:", exepcion.error);
      }
    );
  }

  gerMotorTypebyId(id:number) {
    this.motorService.getMotorTypeById(id).subscribe(
      (respones: ResponseInfo<EngineType>)=>{
      console.log("Data gerMotorTypebyId", respones.data);
      },
      (error:any) =>{
        console.error("Error gerMotorTypebyId:", error);
      }
    );
  }


  getAllMotoraAdapterType() {
    this.motorService.getAllMotorAdapterType().subscribe(
      (response) => {
        console.log('Data getAllMotoraAdapterType:', response.data);
      },
      (error) => {
        console.error('Error getAllMotoraAdapterType:', error);
      }
    );
  }

  getMotorAdapterById(id:number) {
    this.motorService.getMotorAdapterById(id).subscribe(
      (respones: ResponseInfo<EngineAdapterType>)=>{
      console.log("Data getMotorAdapterById", respones.data);
      },
      (error:any) =>{
        console.error("Error getMotorAdapterById:", error);
      }
    );
  }

  getMotorAdapterByMotorTypeId(id:number) {
    this.motorService.getMotorAdapterByMotorTypeId(id).subscribe(
      (respones: ResponseInfo<EngineAdapterType[]>)=>{
      console.log("Data getMotorAdapterByMotorTypeId", respones.data);
      },
      (error:any) =>{
        console.error("Error getMotorAdapterByMotorTypeId:", error);
      }
    );
  }







}
