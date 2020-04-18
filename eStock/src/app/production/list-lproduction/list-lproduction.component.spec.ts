import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListLproductionComponent } from './list-lproduction.component';

describe('ListLproductionComponent', () => {
  let component: ListLproductionComponent;
  let fixture: ComponentFixture<ListLproductionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListLproductionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListLproductionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
