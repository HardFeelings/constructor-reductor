import { CommercialPropItem } from "./commercialPropItem";
import { Manager } from "./manager";

export class CommercialProp {
  idCommercialProp: number;
  number: string;
  partner: string;
  cost: bigint;
  timestamp: string | null;
  marginRatio:number;
  manager: Manager | null;
  commercialPropItems: CommercialPropItem[];
}
