import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MenuComponent } from '../../pages/menu/menu.component';
import { RouterEvent, RouterLink, RouterLinkActive } from '@angular/router';
import { AuthLoginService } from '../../services/login/auth-login.service';

@Component({ 
  selector: 'app-layout',
  standalone: true,
  imports: [CommonModule, MenuComponent,RouterLink,RouterLinkActive],
  templateUrl: './layout.component.html',
  styleUrl: './layout.component.css'
})
export class LayoutComponent {
  isMenuOpen = false;

  toggleMenu() {
    this.isMenuOpen = !this.isMenuOpen;
  }

  constructor(public authService: AuthLoginService){

  }

  logout(): void {
      this.authService.logout();
  }
  

}
