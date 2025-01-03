import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {ReactiveFormsModule } from '@angular/forms'; 

@Component({
  selector: 'app-excuses',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './excuses.component.html',
  styleUrl: './excuses.component.css',
})
export class ExcusesComponent {
  excuseForm: FormGroup;

  constructor(private fb: FormBuilder) {
    this.excuseForm = this.fb.group({
      excuseType: ['', Validators.required],
      excuseClass: ['', Validators.required],
      date1: ['', Validators.required],
      date2: ['', Validators.required],
      reason: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.excuseForm.valid) {
      console.log('Excuse submitted:', this.excuseForm.value);
      // Here you would typically handle the excuse submission logic
    }
    if (this.excuseForm.invalid) {
      console.log('Excuse not submitted:', this.excuseForm.value);
      // Here you would typically handle the excuse submission logic
    }
  }
}
