import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PaperSubmissionComponent } from './paper-submission.component';

describe('PaperSubmissionComponent', () => {
  let component: PaperSubmissionComponent;
  let fixture: ComponentFixture<PaperSubmissionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PaperSubmissionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PaperSubmissionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
