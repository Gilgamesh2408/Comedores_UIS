import { Injectable } from '@angular/core';
import { HttpClient , HttpHeaders} from '@angular/common/http';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { Router } from '@angular/router';
import { catchError } from 'rxjs/operators';
import { of } from 'rxjs';
import { JustificationDTO } from '../../models/Justification.model';
import { MealDTO } from '../../models/Menu.model';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  private adminApi = 'http://localhost:8080/admin'; // Cambia esto según tu configuración
  

  private getAuthHeaders(): HttpHeaders {
    const token = localStorage.getItem('authToken');
    return new HttpHeaders({
      Accept: 'application/json',
      Authorization: `Bearer ${token}`,
    });
    
  }

  constructor(private http: HttpClient, private router: Router) {}

  createMenu(menu: MealDTO): Observable<MealDTO> {
    const payload: MealDTO = menu
    const headers: HttpHeaders = this.getAuthHeaders()
    return this.http.post<MealDTO>(`${this.adminApi}/create/menu`, payload, {headers});
  }

  getAllMenus(): Observable<MealDTO[]> {
    const headers: HttpHeaders = this.getAuthHeaders()
    return this.http.get<MealDTO[]>(`${this.adminApi}/menus`, {headers});
  }

  deleteMenu(id: number): Observable<void> {
    const headers: HttpHeaders = this.getAuthHeaders();
    return this.http.delete<void>(`${this.adminApi}/menus/${id}`, { headers });
  }
  
}
