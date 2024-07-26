
export class Engine {
  id: number;
  power:number;
  frequency: number;
  efficiency:number;
  ratedCurrent:number;
  posTerminalBox:number;
  momentOfInertia:number;
  motorTypeId: EngineType;
  motorAdapterTypeId: EngineAdapterType;
}

export class EngineType {
  idMotorType:number;
  motorTypeName: string;

}

export class EngineAdapterType {
  idMotorAdapterType:number;
  motorAdapterTypeValue: string;
  motorTypeId: EngineType;
}
