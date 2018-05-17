import { TestBed, inject } from '@angular/core/testing';

import { FleetsService } from './fleets.service';

describe('FleetsService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [FleetsService]
    });
  });

  it('should be created', inject([FleetsService], (service: FleetsService) => {
    expect(service).toBeTruthy();
  }));
});
