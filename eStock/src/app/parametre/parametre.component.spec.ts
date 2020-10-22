import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParametreComponent } from './parametre.component';

describe('ParametreComponent', () => {
  let component: ParametreComponent;
  let fixture: ComponentFixture<ParametreComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ParametreComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParametreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
