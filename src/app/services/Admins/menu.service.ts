import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MenuService {

  private apiUrl = 'http://localhost:8080/admin';  // Cambia la URL según tu configuración de backend

  constructor(private http: HttpClient) { }

  createMeal(menuData: any, token: String): Observable<any> {
    const headers = { 'Authorization': `Bearer ${token}`, 
    'Content-Type': 'application/json' };
    
    return this.http.post(`${this.apiUrl}/create/menu`, menuData, {headers});
  }
}
