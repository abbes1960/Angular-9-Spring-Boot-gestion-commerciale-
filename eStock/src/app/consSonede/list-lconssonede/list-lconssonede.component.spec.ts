import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListLconssonedeComponent } from './list-lconssonede.component';

describe('ListLconssonedeComponent', () => {
  let component: ListLconssonedeComponent;
  let fixture: ComponentFixture<ListLconssonedeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListLconssonedeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListLconssonedeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
