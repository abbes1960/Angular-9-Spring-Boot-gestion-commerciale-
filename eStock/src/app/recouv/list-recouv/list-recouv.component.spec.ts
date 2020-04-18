import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListRecouvComponent } from './list-recouv.component';

describe('ListRecouvComponent', () => {
  let component: ListRecouvComponent;
  let fixture: ComponentFixture<ListRecouvComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListRecouvComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListRecouvComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
