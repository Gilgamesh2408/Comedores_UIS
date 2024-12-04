import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { Router } from '@angular/router';
import { catchError } from 'rxjs/operators';
import { of } from 'rxjs';
import { JustificationDTO } from '../../models/Justification.model';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  private getAuthHeaders(): HttpHeaders {
    const token = localStorage.getItem('authToken');
    return new HttpHeaders({
      Accept: 'application/json',
      Authorization: `Bearer ${token}`,
    });
    
  }

  private studentApi = 'http://localhost:8080/student'; // Cambia esto según tu configuración

  constructor(private http: HttpClient, private router: Router) {}

  createJustification(justification: JustificationDTO): Observable<JustificationDTO> {
    const payload = justification
    const headers: HttpHeaders = this.getAuthHeaders();
    return this.http.post<JustificationDTO>(`${this.studentApi}/justification`, payload,{headers});
  }

  getJustifications(): Observable<JustificationDTO[]> {
    const headers: HttpHeaders = this.getAuthHeaders();
    return this.http.get<JustificationDTO[]>(`${this.studentApi}/justification`, { headers });
  }
}
