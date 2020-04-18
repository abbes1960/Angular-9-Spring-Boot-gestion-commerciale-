import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListLrecouvComponent } from './list-lrecouv.component';

describe('ListLrecouvComponent', () => {
  let component: ListLrecouvComponent;
  let fixture: ComponentFixture<ListLrecouvComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListLrecouvComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListLrecouvComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
