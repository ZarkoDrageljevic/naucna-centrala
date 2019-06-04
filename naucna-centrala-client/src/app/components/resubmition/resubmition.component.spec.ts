import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ResubmitionComponent } from './resubmition.component';

describe('ResubmitionComponent', () => {
  let component: ResubmitionComponent;
  let fixture: ComponentFixture<ResubmitionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ResubmitionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ResubmitionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
