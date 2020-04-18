import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddTypefComponent } from './add-typef.component';

describe('AddTypefComponent', () => {
  let component: AddTypefComponent;
  let fixture: ComponentFixture<AddTypefComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddTypefComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddTypefComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
