
export class Engine {
  id: number;
  power:number;
  frequency: number | undefined;
  efficiency:number;
  ratedCurrent:number;
  momentOfInertia:number | undefined;
  motorTypeId: number | undefined;
  motorAdapterTypeId: number | undefined;
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
