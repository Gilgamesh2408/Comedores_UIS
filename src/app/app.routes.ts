import { Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { ExcusesComponent } from './pages/excuses/excuses.component';
import { MenuComponent } from './pages/menu/menu.component';
import { LayoutComponent } from './shared/layout/layout.component';
import { FooterComponent } from './shared/footer/footer.component';
import { AdminComponent } from './admin/admin.component';
import { authGuard } from './guard/auth.guard';
import { authenticatedGuard } from './guard/authenticated.guard';
import { userGuardGuard } from './guard/user-guard.guard';
import { adminGuardGuard } from './guard/admin-guard.guard';

export const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full'},

  { path: 'login', 
    component: LoginComponent, 
    canActivate:[authenticatedGuard]},

  { path: 'menu', 
    component: MenuComponent , 
    canActivate:[authGuard]},

  { path: 'excuse', 
    component: ExcusesComponent, 
    canActivate:[authGuard,userGuardGuard] },

  { path: 'layout', 
    component:LayoutComponent},

  { path: 'footer', 
    component:FooterComponent},

  { path: 'admin',
    component:AdminComponent, 
    canActivate:[authGuard,adminGuardGuard]}
];

