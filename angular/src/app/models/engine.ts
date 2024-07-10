
export class Engine {
  id: number;
  power:number;
  frequency: number;
  rpm: number;
  motorTypeId: EngineType;
  motorAdapterTypeId: EngineAdapterType;
}

export class EngineType {
  id:number;
  motorTypeName: string;

}

export class EngineAdapterType {
  id:number;
  motorAdapterTypeValue: string;
  motorTypeId: EngineType;
}

