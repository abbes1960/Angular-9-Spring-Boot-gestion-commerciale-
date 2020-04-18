import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListLrecoufComponent } from './list-lrecouf.component';

describe('ListLrecoufComponent', () => {
  let component: ListLrecoufComponent;
  let fixture: ComponentFixture<ListLrecoufComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListLrecoufComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListLrecoufComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
