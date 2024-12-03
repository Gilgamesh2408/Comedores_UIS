import { CommonModule, NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormsModule } from '@angular/forms';
import { StudentService } from '../../services/Students/student.service';
import { AuthLoginService } from '../../services/login/auth-login.service';

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

@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [CommonModule, NgIf,FormsModule],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css'
})

export class MenuComponent implements OnInit {
  selectedDay = 'Monday';
  selectedMeal: string | null = null;
  fullWeekMenu: WeekMenu = {}; // Guardar el menú completo de la semana

  constructor(private menuService: StudentService,
              private authService: AuthLoginService) { }

  ngOnInit() {
    this.loadMenu();
  }

  loadMenu() {
    const token = this.authService.getToken(); // Obtiene el token
    if(token){
    
    this.menuService.getMenuByDay(token,this.selectedDay).subscribe({
      next: (menu) => {
      this.fullWeekMenu[this.selectedDay] = menu; // Actualiza el menú con el día seleccionado
      console.log('Menú recibido:', menu);},
      error: (err) => {
        console.error('Error al obtener el menú:', err);
      }
    });
    }else {
      console.error('No se encontró un token de autenticación');
    }
  }

  get days(): string[] {
    return Object.keys(this.fullWeekMenu);
  }

  get selectedDayMenu(): DayMenu {
    return this.fullWeekMenu[this.selectedDay];
  }

  showNutritionInfo(meal: string) {
    this.selectedMeal = meal;
  }

  closeNutritionInfo() {
    this.selectedMeal = null;
  }
}