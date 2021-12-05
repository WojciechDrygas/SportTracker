import { TestBed } from '@angular/core/testing';

import { SportDataService } from './sport-data.service';

describe('SportDataService', () => {
  let service: SportDataService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SportDataService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
