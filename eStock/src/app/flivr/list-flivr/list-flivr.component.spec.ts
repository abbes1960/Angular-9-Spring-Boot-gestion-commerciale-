import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListFlivrComponent } from './list-flivr.component';

describe('ListFlivrComponent', () => {
  let component: ListFlivrComponent;
  let fixture: ComponentFixture<ListFlivrComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListFlivrComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListFlivrComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
