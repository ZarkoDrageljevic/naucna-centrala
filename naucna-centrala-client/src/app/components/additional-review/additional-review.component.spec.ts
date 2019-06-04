import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdditionalReviewComponent } from './additional-review.component';

describe('AdditionalReviewComponent', () => {
  let component: AdditionalReviewComponent;
  let fixture: ComponentFixture<AdditionalReviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdditionalReviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdditionalReviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
