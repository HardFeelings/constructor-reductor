
export class Reducer {
    idReducer: number;
    reducerTypeId: number;
    reducerSizeId: number;
    // diameterInputShaft!: number;
    diameterOutputShaft!: number;
    reducerInputTypeId: number;
    // reducerAdapterTypeId: number;
    reducerOutputShaftTypeId: number;
    reducerInstallationTypeId: number;
    reducerMountingId: number;
    ratio!: number;

}



export class ReducerType {
  idReducerType: number;
  reducerTypeName!: string;
}


export class ReducerAdapterType {
  idReducerAdapterType: number;
  reducerAdapterTypeValue!: string;
  reducerTypeId: number;
}

export class ReducerMounting {
  idReducerMounting: number;
  reducerMountingValue!: string;
}


export class ReducerSize {
  idReducerSize: number
  reducerSizeValue!: string;
  reducerTypeId: number
}


export class ReducerInputType {
  idReducerInputType: number
  reducerInputTypeValue!: string
  reducerTypeId: number
}

export class ReducerOutputShaftType {
  idReducerOutputShaftType: number
  reducerOutputShaftTypeValue!: string
  reducerTypeId: number
}

export class ReducerInstallationType {
  idReducerInstallationType: number
  reducerInstallationTypeValue: string
  reducerTypeId: number
}
