import { CommercialPropItem } from "./commercialPropItem";
import { CommercialPropTerm } from "./commercialPropTerm";
import { Manager } from "./manager";

export class CommercialProp {
  idCommercialProp: number;
  number: string | null;
  partner: string | null;
  cost: bigint;
  timestamp: string | null;
  // marginRatio:number | null;
  manager: Manager | null;
  deliveryTime: number;
  guarantee: number;
  deliveryTerms: string;
  commercialPropItems: CommercialPropItem[];
  commercialPropTerms: CommercialPropTerm[];
}
