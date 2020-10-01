import { TestBed } from '@angular/core/testing';

import { RemoveStoregeService } from './remove-storege.service';

describe('RemoveStoregeService', () => {
  let service: RemoveStoregeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RemoveStoregeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
