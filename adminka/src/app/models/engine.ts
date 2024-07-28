
export class Engine {
  id: number;
  power:number;
  frequency: number | undefined;
  efficiency:number;
  ratedCurrent:number;
  posTerminalBox:number | undefined;
  momentOfInertia:number | undefined;
  // motorTypeId: EngineType;
  // motorAdapterTypeId: EngineAdapterType;
  motorTypeId: number | undefined;
  motorAdapterTypeId: number | undefined;
  cableExitSide:string | undefined;
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
