import { Engine } from "./engine";
import { Reducer } from "./reducer";

export class Product {
  idProduct: number;
  productTypeId: number;
  name: string;
  weight: number;
  price: number;
  reducerId!: number;
  reducer!: Reducer|null;
  motorId!: number;
  motor!: Engine | null;
  optionsIds: number[];
  imageEmpty!: boolean;
  // imageString:string | null;

}
export class ProductType{
  idProductType: number
  productTypeValue: string
}

export class ProductOption {
  idProductOption: number
  productOptionValue: string
  productTypeId: ProductType

}
export enum enProduct {
  Motor = "Мотор",
  Reducer = "Редуктор",
  MotorReducer = "Мотор-редуктор"
}

