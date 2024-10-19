import { Component } from '@angular/core';

interface Meal {
  items: string[];
  nutrition: {
    calories: number;
    protein: string;
    carbs: string;
    fat: string;
  };
}

interface DayMenu {
  [key: string]: Meal;
}

@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [],
  template: './menu.component.html',
  styleUrl: './menu.component.css'
})
export class MenuComponent {
  selectedDay = 'Lunes';
  selectedMeal= '';
  days = ['Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado', 'Domingo'];
  mealTypes = ['Desayuno', 'Almuerzo', 'Cena'];

  menu: { [key: string]: DayMenu } = {
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
    // Add menus for other days here
  };

  showNutritionInfo(meal: string) {
    this.selectedMeal = meal;
  }

  closeNutritionInfo() {
    this.selectedMeal = '';
  }
}
