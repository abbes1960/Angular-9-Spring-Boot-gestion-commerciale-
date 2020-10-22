import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Accueil1Component } from './accueil1.component';

describe('Accueil1Component', () => {
  let component: Accueil1Component;
  let fixture: ComponentFixture<Accueil1Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Accueil1Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Accueil1Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
