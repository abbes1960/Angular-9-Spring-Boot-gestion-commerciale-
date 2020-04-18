import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListPreleveComponent } from './list-preleve.component';

describe('ListPreleveComponent', () => {
  let component: ListPreleveComponent;
  let fixture: ComponentFixture<ListPreleveComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListPreleveComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListPreleveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
