import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {ReactiveFormsModule } from '@angular/forms'; 
import { AuthLoginService } from '../../services/login/auth-login.service';
import { Router } from '@angular/router';
import { StudentService } from '../../services/Students/student.service';

@Component({
  selector: 'app-excuses',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './excuses.component.html',
  styleUrl: './excuses.component.css',
})
export class ExcusesComponent {
  excuseForm: FormGroup;

  constructor(private fb: FormBuilder,
      private authService: AuthLoginService,
      private studentService: StudentService
  ) {
    this.excuseForm = this.fb.group({
      excuseType: ['PERMANENTE', Validators.required],
      excuseClass: ['Desayuno', Validators.required],
      date1: ['', Validators.required],
      date2: ['', Validators.required],
      reason: ['', Validators.required]
    });
  }
  // Método para obtener el menú de un día específico
  getMenuForDay(day: string) {
    const token = this.authService.getToken(); // Obtiene el token
    //console.log("Token en submitJustification:", token);   //revisa el token
    if (token) {
      this.studentService.getMenu(token, day).subscribe({
        next: (response) => {
          console.log('Menú recibido:', response);
        },
        error: (err) => {
          console.error('Error al obtener el menú:', err);
        }
      });
    } else {
      console.error('No se encontró un token de autenticación');
    }
  }

  // Método para enviar una justificación
  crearJustificacion() {
    const token = this.authService.getToken();
    if (!token) {
    console.error('No token encontrado');
    return;
    }
    if (this.excuseForm.valid) {
      const justificationData = this.excuseForm.value;
      
      // onSubmit logic

      const justification = {
        type: justificationData.excuseType,  // Ejemplo: 'Permanente o Esporadica'
        //class: justificationData.excuseClass, // clase (Desayuno, Almuerzo, Comida)
        dateInit: justificationData.date1,  // Formato 'YYYY-MM-DD'
        dateEnd: justificationData.date2,  // Formato 'YYYY-MM-DD'
        description: justificationData.reason  // Motivo de la excusa
      };
      

      //this.excuseForm.value.excuseType, this.excuseForm.value.excuseClass, this.excuseForm.value.date1, this.excuseForm.value.date2, this.excuseForm.value.reason
      this.studentService.sendJustification(justification, token).subscribe({
        next: (response)=> {
          if (response) {
          console.log('Justificacion exitosa', response);
        } else {
          console.error('Error en la respuesta', response);
        }
        },
        error: (err) => console.error('Justification failed', err)
      })

      // Opcional: mostrar un mensaje de éxito
      alert('Excusa registrada con éxito');
    }
    if (this.excuseForm.invalid) {
      alert('Por favor, completa todos los campos requeridos.');
      console.log('Excuse not submitted:', this.excuseForm.value);
      // Here you would typically handle the excuse submission logic
    }
    this.excuseForm.reset();// Limpiar el formulario después de enviar
  }
}
