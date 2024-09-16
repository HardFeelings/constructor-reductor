import { HttpClient } from "@angular/common/http";
import { Observable } from 'rxjs';
import { ResponseInfo } from "../models/responesInfo";

export class PaymentTerms {
  idPaymentTerms: number;
  visibleName: string;
  fullName: string;

  delete(http: HttpClient) : Observable<ResponseInfo<boolean>>  {
    return http.delete<ResponseInfo<boolean>>(`/api/v1/security/admin/paymentTerms/${this.idPaymentTerms}`)
}
  ser(): any {
    return {
      idPaymentTerms: this.idPaymentTerms,
      visibleName: this.visibleName,
      fullName: this.fullName,
    }
  }

  save(http: HttpClient) : Observable<ResponseInfo<PaymentTerms>> {
    return http.post<ResponseInfo<PaymentTerms>>('/api/v1/security/admin/paymentTerms', this.ser())
  }
}
