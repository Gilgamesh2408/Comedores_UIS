import { CanActivateFn, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { inject, Injectable } from '@angular/core';
import { AuthLoginService } from '../services/login/auth-login.service';



export const userGuardGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthLoginService);
  const router = inject(Router);
  
  if(authService.isUser()){
    return true
  } else {
    return router.navigate(['/menu'])
  }
};
