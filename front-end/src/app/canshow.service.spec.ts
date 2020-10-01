import { TestBed } from '@angular/core/testing';

import { CanshowService } from './canshow.service';

describe('CanshowService', () => {
  let service: CanshowService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CanshowService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
