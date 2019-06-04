import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormatResubmitionComponent } from './format-resubmition.component';

describe('FormatResubmitionComponent', () => {
  let component: FormatResubmitionComponent;
  let fixture: ComponentFixture<FormatResubmitionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormatResubmitionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormatResubmitionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
