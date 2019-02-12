import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UploadMagazineComponent } from './upload-magazine.component';

describe('UploadMagazineComponent', () => {
  let component: UploadMagazineComponent;
  let fixture: ComponentFixture<UploadMagazineComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UploadMagazineComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UploadMagazineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
