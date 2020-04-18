import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListLinventComponent } from './list-linvent.component';

describe('ListLinventComponent', () => {
  let component: ListLinventComponent;
  let fixture: ComponentFixture<ListLinventComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListLinventComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListLinventComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
