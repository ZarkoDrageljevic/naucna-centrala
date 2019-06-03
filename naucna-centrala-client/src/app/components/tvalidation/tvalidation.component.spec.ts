import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TvalidationComponent } from './tvalidation.component';

describe('TvalidationComponent', () => {
  let component: TvalidationComponent;
  let fixture: ComponentFixture<TvalidationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TvalidationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TvalidationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
