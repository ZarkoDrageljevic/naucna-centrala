import { TestBed } from '@angular/core/testing';

import { UploadMagazineService } from './upload-magazine.service';

describe('UploadMagazineService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: UploadMagazineService = TestBed.get(UploadMagazineService);
    expect(service).toBeTruthy();
  });
});
