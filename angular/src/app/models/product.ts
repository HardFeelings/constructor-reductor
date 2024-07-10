export class Product {
  id: number;
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
  id: number
  value: string
}

export class ProductOption {
  id: number
  productOptionValue: string
  productTypeId: ProductType

}
