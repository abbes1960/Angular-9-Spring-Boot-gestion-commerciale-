import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListLbs1016Component } from './list-lbs1016.component';

describe('ListLbs1016Component', () => {
  let component: ListLbs1016Component;
  let fixture: ComponentFixture<ListLbs1016Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListLbs1016Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListLbs1016Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
