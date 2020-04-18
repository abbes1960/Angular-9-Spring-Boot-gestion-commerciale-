import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListFllivrComponent } from './list-fllivr.component';

describe('ListFllivrComponent', () => {
  let component: ListFllivrComponent;
  let fixture: ComponentFixture<ListFllivrComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListFllivrComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListFllivrComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
