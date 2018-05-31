import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CityProductsComponent } from './city-products.component';

describe('CityProductsComponent', () => {
  let component: CityProductsComponent;
  let fixture: ComponentFixture<CityProductsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CityProductsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CityProductsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
