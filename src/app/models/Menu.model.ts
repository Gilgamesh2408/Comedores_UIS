export interface MealDTO {
    id?: number;
    mainCourse: string;
    soup: string;
    drink: string;
    dessert: string;
    createdAt: Date;
    ingredientsDTO: IngredientDTO[];
  }
  
  export interface IngredientDTO {
    name: string;
    quantity: number;
  }
  