import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RejectExplanationComponent } from './reject-explanation.component';

describe('RejectExplanationComponent', () => {
  let component: RejectExplanationComponent;
  let fixture: ComponentFixture<RejectExplanationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RejectExplanationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RejectExplanationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
