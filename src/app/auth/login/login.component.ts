import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthLoginService } from '../../services/login/auth-login.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
  imports: [ReactiveFormsModule,CommonModule],
  standalone: true,
})

export class LoginComponent implements OnInit {
  loginForm!: FormGroup;
  constructor(
    private fb: FormBuilder,
    private authService: AuthLoginService,
    private router: Router
    
    
  
  ){} ngOnInit(): void{
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      isAdmin: [false]// Campo adicional para indicar si el usuario es admin
    });
  }
  
  login(): void {
    
    if (this.loginForm.valid) {
      const loginData = this.loginForm.value;
      //console.log('Datos del formulario;', loginData)
      this.authService.login(this.loginForm.value.username, this.loginForm.value.password, this.loginForm.value.isAdmin).subscribe({
        next: (response)=> {
          if (response) {
          console.log('Login exitoso', response);
          this.router.navigate(['/menu']); // Redirige a la página principal
        } else {
          console.error('Error en la respuesta', response);
        }
        },
        
        error: (err) => console.error('Login failed', err)
      })
    } else {
      console.warn('El formulario no es válido.');
    }
    
  }

  onLogout() {
    this.authService.logout(); // Llama al servicio para hacer logout
  }


  
}