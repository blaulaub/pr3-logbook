import { TestBed, inject } from '@angular/core/testing';

import { CityProductsService } from './city-products.service';

describe('CityProductsService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CityProductsService]
    });
  });

  it('should be created', inject([CityProductsService], (service: CityProductsService) => {
    expect(service).toBeTruthy();
  }));
});
