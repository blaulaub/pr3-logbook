import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FleetAddComponent } from './fleet-add.component';

describe('FleetAddComponent', () => {
  let component: FleetAddComponent;
  let fixture: ComponentFixture<FleetAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FleetAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FleetAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
