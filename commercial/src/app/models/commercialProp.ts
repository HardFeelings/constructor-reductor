import { CommercialPropItem } from "./commercialPropItem";
import { Manager } from "./manager";

export class CommercialProp {
  idCommercialProp: number;
  number: string | null;
  partner: string | null;
  cost: bigint;
  timestamp: string | null;
  marginRatio:number | null;
  manager: Manager | null;
  commercialPropItems: CommercialPropItem[];
}
