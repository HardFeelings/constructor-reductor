
export class Reducer {
    id: number;
    reducerTypeId: number;
    reducerSizeId: number;
    diameterInputShaft!: number;
    diameterOutputShaft!: number;
    reducerInputTypeId: number;
    reducerAdapterTypeId: number;
    reducerOutputShaftTypeId: number;
    reducerInstallationTypeId: number;
    reducerMountingId: number;
    torqueMoment!: number;
    ratio!: number;

    // mounting: MountingPoint;
    // installationType: ReducerInstallationType;
    // outputShaftType: ReducerOutputShaftType;
    // adapterType: ReducerAdapterType;
    // inputType: ReducerInputType;
    // size: ReducerSize;
    // type: ReducerType;
}



export class ReducerType {
  id: number;
  reducerTypeName!: string;
}


export class ReducerAdapterType {
  id: number;
  value!: string;
  reducerTypeId: number;
}

export class MountingPoint {
  id: number;
  value!: string;
}


export class ReducerSize {
  id: number
  reducerSizeValue!: string;
  reducerTypeId: number
}


export class ReducerInputType {
  id: number
  value!: string
  reducerTypeId: number
}

export class ReducerOutputShaftType {
  id: number
  value!: string
  reducerTypeId: number
}

export class ReducerInstallationType {
  id: number
  value: string
  reducerTypeId: number
}
