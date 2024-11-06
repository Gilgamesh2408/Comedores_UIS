import { CommonModule, NgIf } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormsModule } from '@angular/forms';

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

export class MenuComponent {
  selectedDay = 'Lunes';
  selectedMeal: string | null = null;

  FULL_WEEK_MENU: WeekMenu = {
    'Lunes': {
      'Desayuno': {
        items: ['Huevos revueltos', 'Pan integral', 'Jugo de naranja'],
        nutrition: { calories: 450, protein: '15g', carbs: '60g', fat: '15g' }
      },
      'Almuerzo': {
        items: ['Pollo a la plancha', 'Arroz', 'Ensalada', 'Sopa de verduras'],
        nutrition: { calories: 650, protein: '35g', carbs: '80g', fat: '20g' }
      },
      'Cena': {
        items: ['Pescado al horno', 'Puré de papas', 'Vegetales al vapor'],
        nutrition: { calories: 550, protein: '30g', carbs: '65g', fat: '18g' }
      }
    },
    'Martes': {
      'Desayuno': {
        items: ['Tamal', 'Pan integral', 'Chocolate'],
        nutrition: { calories: 450, protein: '15g', carbs: '60g', fat: '15g' }
      },
      'Almuerzo': {
        items: ['Carne asada', 'Arroz', 'Ensalada', 'Sopa de verduras'],
        nutrition: { calories: 650, protein: '35g', carbs: '80g', fat: '20g' }
      },
      'Cena': {
        items: ['Avena', 'Vegetales al vapor'],
        nutrition: { calories: 550, protein: '30g', carbs: '65g', fat: '18g' }
      }
    },

    'Miercoles': {
      'Desayuno': {
        items: ['Huevos revueltos', 'Pan integral', 'Jugo de naranja'],
        nutrition: { calories: 450, protein: '15g', carbs: '60g', fat: '15g' }
      },
      'Almuerzo': {
        items: ['Pollo a la plancha', 'Arroz', 'Ensalada', 'Sopa de verduras'],
        nutrition: { calories: 650, protein: '35g', carbs: '80g', fat: '20g' }
      },
      'Cena': {
        items: ['Pescado al horno', 'Puré de papas', 'Vegetales al vapor'],
        nutrition: { calories: 550, protein: '30g', carbs: '65g', fat: '18g' }
      }
    },

    'Jueves': {
      'Desayuno': {
        items: ['Huevos revueltos', 'Pan integral', 'Jugo de naranja'],
        nutrition: { calories: 450, protein: '15g', carbs: '60g', fat: '15g' }
      },
      'Almuerzo': {
        items: ['Pollo a la plancha', 'Arroz', 'Ensalada', 'Sopa de verduras'],
        nutrition: { calories: 650, protein: '35g', carbs: '80g', fat: '20g' }
      },
      'Cena': {
        items: ['Pescado al horno', 'Puré de papas', 'Vegetales al vapor'],
        nutrition: { calories: 550, protein: '30g', carbs: '65g', fat: '18g' }
      }
    },

    'Viernes': {
      'Desayuno': {
        items: ['Huevos revueltos', 'Pan integral', 'Jugo de naranja'],
        nutrition: { calories: 450, protein: '15g', carbs: '60g', fat: '15g' }
      },
      'Almuerzo': {
        items: ['Pollo a la plancha', 'Arroz', 'Ensalada', 'Sopa de verduras'],
        nutrition: { calories: 650, protein: '35g', carbs: '80g', fat: '20g' }
      },
      'Cena': {
        items: ['Pescado al horno', 'Puré de papas', 'Vegetales al vapor'],
        nutrition: { calories: 550, protein: '30g', carbs: '65g', fat: '18g' }
      }
    },
  };

  get days(): string[] {
    return Object.keys(this.FULL_WEEK_MENU);
  }

  get selectedDayMenu(): DayMenu {
    return this.FULL_WEEK_MENU[this.selectedDay];
  }

  showNutritionInfo(meal: string) {
    this.selectedMeal = meal;
  }

  closeNutritionInfo() {
    this.selectedMeal = null;
  }
}