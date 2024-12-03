import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { AuthLoginService } from '../../services/login/auth-login.service';
import { Observable } from 'rxjs';

interface Nutrition {
  calories: number;
  protein: string;
  carbs: string;
  fat: string;
}

interface Meal {
  items: string[];
  nutrition: Nutrition;
}

interface DayMenu {
  [key: string]: Meal;
}

interface WeekMenu {
  [key: string]: DayMenu;
}

@Injectable({
  providedIn: 'root'
})
export class StudentService {


  //URLs de los endpoints
  private justification_URL = 'http://localhost:8080/student/justification';
  private menu_URL = 'http://localhost:8080/student';

  constructor(private http: HttpClient, 
              private router: Router,
              private authService: AuthLoginService) { }

              
  //LOGICA PARA MANEJAR LOS MENUS SOLICITADOS

  // Obtener el menú para un día específico, usando el token de autorización
  getMenuByDay(token: string, day: string) {
    const headers = {
      'Authorization': `Bearer ${token}`  // Enviar el token en los encabezados
    };
    return this.http.get<DayMenu>(`${this.menu_URL}/${day}`, { headers });
  }


  //LOGICA PARA MANEJAR LAS JUSTIFICACIONES DE LOS ESTDIANTES

  // Enviar Justificación al Backend
  sendJustification(justification: any, token: String) {
    const headers = { 'Authorization': `Bearer ${token}`, 
    'Content-Type': 'application/json' };
    
    return this.http.post(this.justification_URL, justification, { headers });
  }


}
