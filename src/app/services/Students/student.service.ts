import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { AuthLoginService } from '../../services/login/auth-login.service';

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

  // LOGICA PARA MANEJAR LOS MENUS SOLICITADOS
  // Obtener el menú para un día específico, usando el token de autorización
  getMenu(token: string, day: string) {
    const headers = {
      'Authorization': `Bearer ${token}`  // Enviar el token en los encabezados
    };
    return this.http.get(`${this.menu_URL}/${day}`, { headers });
  }

  
  //LOGICA PARA MANEJAR LAS JUSTIFICACIONES DE LOS ESTDIANTES

  // Enviar Justificación al Backend
  sendJustification(justification: any, token: String) {
    const headers = { 'Authorization': `Bearer ${token}`, 
    'Content-Type': 'application/json' };
    
    return this.http.post(this.justification_URL, justification, { headers });
  }

}
