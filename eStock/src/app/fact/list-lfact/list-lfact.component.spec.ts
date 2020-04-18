import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListLfactComponent } from './list-lfact.component';

describe('ListLfactComponent', () => {
  let component: ListLfactComponent;
  let fixture: ComponentFixture<ListLfactComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListLfactComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListLfactComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
