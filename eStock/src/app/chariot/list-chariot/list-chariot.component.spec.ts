import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListChariotComponent } from './list-chariot.component';

describe('ListChariotComponent', () => {
  let component: ListChariotComponent;
  let fixture: ComponentFixture<ListChariotComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListChariotComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListChariotComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
