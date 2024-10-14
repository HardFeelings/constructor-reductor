export class Filter{

  productTypeId:number;
  productOptions:number[];
  rpm!: number | undefined;
  // motor param
  motorTypeId: number | undefined;
  motorAdapterTypeId!: number | undefined;
  power!: number | undefined;
  polesNumber:number | undefined;

  // reducer param

  idReducerType: number | undefined;
  idReducerSize: number | undefined;
  diamOutput:number | undefined;
  diamOutputAllowance: number ;
  idReducerInputType: number | undefined;
  idReducerOutputShaftType: number | undefined;
  torqueMoment: number | undefined;
  ratio: number | undefined;
  idReducerInstallationType: number | undefined;
  idReducerMounting: number | undefined;
}
