export class Product {
  idProduct: number;
  productTypeId: number;
  name: string;
  weight: number;
  price: number;
  reducerId!: number;
  motorId!: number;
  optionsIds: number[];
  // optionsString: string;

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

