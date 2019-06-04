import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FixPaperComponent } from './fix-paper.component';

describe('FixPaperComponent', () => {
  let component: FixPaperComponent;
  let fixture: ComponentFixture<FixPaperComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FixPaperComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FixPaperComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
