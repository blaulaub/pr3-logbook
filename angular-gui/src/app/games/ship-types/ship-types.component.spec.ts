import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShipTypesComponent } from './ship-types.component';

describe('ShipTypesComponent', () => {
  let component: ShipTypesComponent;
  let fixture: ComponentFixture<ShipTypesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShipTypesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShipTypesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
