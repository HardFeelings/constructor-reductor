import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DataService {
  private id: number | null = null;

  constructor() { }


  setId(id: number | null): void {
    this.id = id;
  }

  getId(): number | null {
    return this.id;
  }
}
