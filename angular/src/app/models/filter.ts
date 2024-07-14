export class Filter{

  productTypeId:number;
  productOptions:number[];

  // motor param
  motorTypeId: number;
  motorAdapterTypeId!: number;
  power!: number;
  frequency!: number;
  rpm!: number;

  // reducer param

  idReducerType: number;
  idReducerSize: number;
  diamInput: number;
  diamInputAllowance: number;
  diamOutput:number;
  diamOutputAllowance: number;
  idReducerInputType: number;
  idReducerAdapterInputType: number;
  idReducerOutputShaftType: number;
  torqueMoment: number;
  ratio: number;
  idReducerInstallationType: number;
  idReducerMounting: number;
}
