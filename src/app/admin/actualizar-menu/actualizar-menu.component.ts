import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MenuService } from '../../services/Admins/menu.service';
import { IngredientDtoModule } from '../../models/ingredient-dto/ingredient-dto.module';
import {ReactiveFormsModule } from '@angular/forms'; 
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-actualizar-menu',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './actualizar-menu.component.html',
  styleUrl: './actualizar-menu.component.css'
})
export class ActualizarMenuComponent implements OnInit {

  menuForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private menuService: MenuService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.menuForm = this.fb.group({
      mainCourse: ['', Validators.required],
      soup: ['', Validators.required],
      drink: ['', Validators.required],
      dessert: ['', Validators.required],
      ingredientsDTO: this.fb.array([this.createIngredient()])
    });
  }
  // Crear un ingrediente en el FormArray
  createIngredient(): FormGroup {
    return this.fb.group({
      name: ['', Validators.required],
      quantity: ['', [Validators.required, Validators.pattern('^[0-9]*$')]]
    });
  }

  // Obtener el FormArray de ingredientes
  get ingredients(): FormArray {
    return this.menuForm.get('ingredientsDTO') as FormArray;
  }

  // Agregar un nuevo ingrediente
  addIngredient() {
    this.ingredients.push(this.createIngredient());
  }

  // Eliminar un ingrediente
  removeIngredient(index: number) {
    this.ingredients.removeAt(index);
  }

  onSubmit() {
    if (this.menuForm.valid) {
      const formData = this.menuForm.value;
  
      // Crear el objeto según la estructura de MealDTO
      const menuData = {
        mainCourse: formData.mainCourse,
        soup: formData.soup,
        drink: formData.drink,
        dessert: formData.dessert,
        createdAt: new Date(),
        ingredientsDTO: formData.ingredientsDTO.map((ingredient: IngredientDtoModule) => ({
          name: ingredient.name,
          quantity: ingredient.quantity
        }))
      };
  
      // Enviar los datos al backend
      this.menuService.createMeal(menuData).subscribe(response => {
        console.log('Menú actualizado correctamente:', response);
        this.router.navigate(['/admin']);  // Redirigir al panel admin
      }, error => {
        console.error('Error al actualizar el menú:', error);
      });
    }
  }

}
