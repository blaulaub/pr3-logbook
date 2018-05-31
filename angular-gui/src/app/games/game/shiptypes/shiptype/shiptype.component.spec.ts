import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShiptypeComponent } from './shiptype.component';

describe('ShiptypeComponent', () => {
  let component: ShiptypeComponent;
  let fixture: ComponentFixture<ShiptypeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShiptypeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShiptypeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
