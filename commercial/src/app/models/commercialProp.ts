import { CommercialPropItem } from "./commercialPropItem";
import { Manager } from "./manager";

export class CommercialProp {
  idCommercialProp: number;
  number: string;
  partner: string;
  cost: bigint;
  timestamp: Date;
  manager: Manager;
  commercialPropItems: CommercialPropItem[];
}
