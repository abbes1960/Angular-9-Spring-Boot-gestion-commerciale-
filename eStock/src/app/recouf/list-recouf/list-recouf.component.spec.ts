import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListRecoufComponent } from './list-recouf.component';

describe('ListRecoufComponent', () => {
  let component: ListRecoufComponent;
  let fixture: ComponentFixture<ListRecoufComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListRecoufComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListRecoufComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
