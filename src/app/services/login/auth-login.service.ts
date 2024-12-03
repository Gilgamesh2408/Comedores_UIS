import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { Router } from '@angular/router';
import { catchError } from 'rxjs/operators';
import { of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthLoginService {
  //currentUserLoginOn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  //currentUserData: BehaviorSubject<User> = new BehaviorSubject<User>(id:0, email:'');
  private loginUrl = 'http://localhost:8080/auth/login'; // Cambia esto según tu configuración
  private tokenkey= 'authToken'

  constructor(private http: HttpClient, private router: Router) {}

  login(username: string, password: string, isAdmin: boolean): Observable<any> {
    const payload = { username, password, isAdmin };
    return this.http.post<any>(this.loginUrl, payload).pipe(
      tap(response => {
        if(response){
          this.setToken(response.jwt)
          //console.log('token', this.getToken());
          
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
    const payload = JSON.parse(atob(token.split('.')[1]));
    const exp = payload.exp * 1000;
    return Date.now() < exp
  }

  logout(): void{
    localStorage.removeItem(this.tokenkey);
    this.router.navigate(['/login']);
  }

}

