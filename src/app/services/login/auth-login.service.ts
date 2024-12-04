import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { Router } from '@angular/router';
import { catchError } from 'rxjs/operators';
import { of } from 'rxjs';
import { jwtDecode } from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class AuthLoginService {
  //currentUserLoginOn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  //currentUserData: BehaviorSubject<User> = new BehaviorSubject<User>(id:0, email:'');
  private loginUrl = 'http://localhost:8080/auth/login'; // Cambia esto según tu configuración
  private tokenkey= 'authToken';

  constructor(private http: HttpClient, private router: Router) {}

  login(username: string, password: string, isAdmin: boolean): Observable<any> {
    const payload = { username, password, isAdmin };
    return this.http.post<any>(this.loginUrl, payload).pipe(
      tap(response => {
        if(response){
          this.setToken(response.jwt)
          console.log('token', this.getToken());
          
        } else {
          console.error('No se recibió token en la respuesta');
        }
      }),
      catchError((error) => {
        console.error('Error de autenticación', error);
        return of(null); // Retorna un valor nulo en caso de error
      })
    );
  
  }
  private setToken(token: string): void{
    localStorage.setItem(this.tokenkey, token)
  }
  getToken(): string | null{
    if(typeof window !== 'undefined'){
      const token = localStorage.getItem(this.tokenkey)
      return token
    } else {
      return null
    }
    
  }
  isAuthenticated(): boolean {
    const token = this.getToken();
    if(!token){
      return false;
    }
    try {
    const payload = JSON.parse(atob(token.split('.')[1]));
    const exp = payload.exp * 1000;
    return Date.now() < exp
    }catch(error){
    console.error('Error decodificando el token', error);
    return false;
    }
  }

  getUserRoles(): string[] {
    const token = this.getToken();
    if (!token) return [];
    try {
      const payload = jwtDecode<any>(token);
      const role= payload.role; //extrae el rol del sistema
      return role ? [role]: []
    } catch (error) {
      console.error('Error decodificando el token', error);
      return [];
    }
  }

  isAdmin(): boolean {
    return this.getUserRoles().includes('ROLE_ADMIN');
  }
  
  isUser(): boolean {
    return this.getUserRoles().includes('ROLE_USER');
  }



  logout(): void{
    localStorage.removeItem(this.tokenkey);
    this.router.navigate(['/login']);
  }

}

