export class Filter{

  productTypeId:number;
  productOptions:number[];

  // motor param
  motorTypeId: number | undefined;
  motorAdapterTypeId!: number | undefined;
  power!: number;
  // posTerminalBox!:number | undefined;
  rpm!: number;

  // reducer param

  idReducerType: number | undefined;
  idReducerSize: number | undefined;
  // diamInput: number;
  // diamInputAllowance: number;
  diamOutput:number;
  diamOutputAllowance: number;
  idReducerInputType: number | undefined;
  // idReducerAdapterInputType: number | undefined;
  idReducerOutputShaftType: number | undefined;
  torqueMoment: number;
  ratio: number;
  idReducerInstallationType: number | undefined;
  idReducerMounting: number | undefined;
}
