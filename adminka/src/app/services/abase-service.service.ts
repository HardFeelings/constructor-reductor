import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

export class ABaseServiceService {

  protected endpoint: string;

  constructor(protected http: HttpClient, endpoint: string) {
    this.endpoint = endpoint;
  }


  protected get<T>(url: string, params?: HttpParams): Observable<T> {
    return this.http.get<T>(`${this.endpoint}/${url}`, { params });
  }

  protected getwp<T>(url: string): Observable<T> {
    return this.http.get<T>(`${this.endpoint}/${url}`);
  }

  protected post<T>(url: string, body: any,  params?: HttpParams): Observable<T> {
    return this.http.post<T>(`${this.endpoint}/${url}`, body, { params });
  }

  protected postwp<T>(url: string, body: any): Observable<T> {
    return this.http.post<T>(`${this.endpoint}/${url}`, body);
  }

  protected put<T>(url: string, body: any): Observable<T> {
    return this.http.put<T>(`${this.endpoint}/${url}`, body);
  }

  protected delete<T>(url: string): Observable<T> {
    return this.http.delete<T>(`${this.endpoint}/${url}`);
  }
}
