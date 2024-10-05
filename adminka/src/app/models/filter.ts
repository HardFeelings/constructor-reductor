export class Filter{

  productTypeId:number;
  productOptions:number[];
  rpm!: number;
  // motor param
  motorTypeId: number | undefined;
  motorAdapterTypeId!: number | undefined;
  power!: number;
  polesNumber:number;

  // reducer param

  idReducerType: number | undefined;
  idReducerSize: number | undefined;
  diamOutput:number;
  diamOutputAllowance: number ;
  idReducerInputType: number | undefined;
  idReducerOutputShaftType: number | undefined;
  torqueMoment: number;
  ratio: number;
  idReducerInstallationType: number | undefined;
  idReducerMounting: number | undefined;
}
