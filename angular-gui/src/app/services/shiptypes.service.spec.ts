import { TestBed, inject } from '@angular/core/testing';

import { ShiptypesService } from './shiptypes.service';

describe('ShiptypesService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ShiptypesService]
    });
  });

  it('should be created', inject([ShiptypesService], (service: ShiptypesService) => {
    expect(service).toBeTruthy();
  }));
});
