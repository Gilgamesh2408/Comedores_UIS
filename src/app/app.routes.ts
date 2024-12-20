import { Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { ExcusesComponent } from './pages/excuses/excuses.component';
import { MenuComponent } from './pages/menu/menu.component';
import { LayoutComponent } from './shared/layout/layout.component';
import { FooterComponent } from './shared/footer/footer.component';
import { AdminComponent } from './admin/admin.component';

export const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'menu', component: MenuComponent },
  { path: 'excuse', component: ExcusesComponent },
  {path: 'layout', component:LayoutComponent},
  {path: 'footer', component:FooterComponent},
  {path: 'admin',component:AdminComponent}
];

