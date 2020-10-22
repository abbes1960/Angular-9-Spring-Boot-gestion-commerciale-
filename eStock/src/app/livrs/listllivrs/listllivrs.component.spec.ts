import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListllivrsComponent } from './listllivrs.component';

describe('ListllivrsComponent', () => {
  let component: ListllivrsComponent;
  let fixture: ComponentFixture<ListllivrsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListllivrsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListllivrsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
