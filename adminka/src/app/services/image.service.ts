import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ImageService {
  private imageUrl = 'api/v1/product/downloadImage';

  constructor(protected http: HttpClient) {
  }



  downloadImageById(id: number, filename: string): void {
    this.http.get(`${this.imageUrl}/${id}`, { responseType: 'blob' }).subscribe(
      (blob: Blob) => {
        this.downloadBlob(blob, `${filename}.jpg`);
      },
      (error) => {
        console.error('Error downloading the image:', error);
      }
    );
  }

  downloadBlob(blob: Blob, filename: string): void {
    const url = window.URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = filename;
    document.body.appendChild(a);
    a.click();
    window.URL.revokeObjectURL(url);
    document.body.removeChild(a);
  }
}
