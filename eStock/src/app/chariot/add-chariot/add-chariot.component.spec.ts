import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddChariotComponent } from './add-chariot.component';

describe('AddChariotComponent', () => {
  let component: AddChariotComponent;
  let fixture: ComponentFixture<AddChariotComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddChariotComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddChariotComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
