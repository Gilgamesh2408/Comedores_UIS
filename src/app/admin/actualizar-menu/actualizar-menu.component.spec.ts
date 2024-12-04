import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActualizarMenuComponent } from './actualizar-menu.component';

describe('ActualizarMenuComponent', () => {
  let component: ActualizarMenuComponent;
  let fixture: ComponentFixture<ActualizarMenuComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ActualizarMenuComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ActualizarMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
