import { Component, OnInit } from '@angular/core';
import { AdminService } from '../services/Admins/admin.service';
import { MealDTO } from '../models/Menu.model';
import { JustificationDTO } from '../models/Justification.model';
import { StudentService } from '../services/Students/student.service';

@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [],
  templateUrl: './admin.component.html',
  styleUrl: './admin.component.css'
})
export class AdminComponent implements OnInit {
  menu: MealDTO = { 
    mainCourse: "", 
    soup: "", 
    drink: "", 
    dessert: "", 
    createdAt: new Date(), 
    ingredientsDTO: [] 
  }; // Menú con valores por defecto
  justifications: JustificationDTO[] = [];
  constructor(
    private readonly adminService: AdminService,
    private readonly studentService: StudentService
  ){}

  ngOnInit(): void {
    this.menu.createdAt = new Date(); // Inicializar la fecha por defecto
  }

  actualizarMenu(): void {
    this.adminService.createMenu(this.menu).subscribe({
      next: (response) => {
        console.log('Menu creado con éxito:', response);
        this.resetMenu(); // Reiniciar el formulario después de crear el menú
      },
      error: (err) => {
        console.error('Error al crear el menú:', err);
      }
    });
  }

  
  // leerExcusas(): void {
  //   this.studentService.getJustifications().subscribe({
  //     next: (data) => {
  //       this.justifications = data as JustificationDTO[]; // Almacena las excusas en la propiedad
  //       console.log('Excusas cargadas:', this.justifications);
  //     },
  //     error: (err) => {
  //       console.error('Error al cargar las excusas:', err as string);
  //     }
  //   });
  // }

  private resetMenu(): void {
    this.menu = { 
      mainCourse: "", 
      soup: "", 
      drink: "", 
      dessert: "", 
      createdAt: new Date(), 
      ingredientsDTO: [] 
    };
  }
}