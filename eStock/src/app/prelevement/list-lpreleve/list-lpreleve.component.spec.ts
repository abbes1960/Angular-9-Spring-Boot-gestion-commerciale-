import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListLpreleveComponent } from './list-lpreleve.component';

describe('ListLpreleveComponent', () => {
  let component: ListLpreleveComponent;
  let fixture: ComponentFixture<ListLpreleveComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListLpreleveComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListLpreleveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
