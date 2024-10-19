import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink,RouterLinkActive,RouterOutlet } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { ExcusesComponent } from './pages/excuses/excuses.component';
import { MenuComponent } from './pages/menu/menu.component';
import { LayoutComponent } from './shared/layout/layout.component';
import { FooterComponent } from './shared/footer/footer.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterLink,RouterOutlet, CommonModule,RouterLinkActive, 
    LoginComponent,ExcusesComponent, MenuComponent,LayoutComponent,
    LayoutComponent,FooterComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'comedores_UIS';
}
