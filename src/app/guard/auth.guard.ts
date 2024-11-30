import { CanActivateFn, Router } from '@angular/router';
import { AuthLoginService } from '../services/login/auth-login.service';
import { inject } from '@angular/core';


export const authGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthLoginService);
  const router = inject(Router);

  if(authService.isAuthenticated()){
    return true
  } else {
    return router.navigate(['/login'])
  }



};
