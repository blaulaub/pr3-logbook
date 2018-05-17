import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShiptypeAddComponent } from './shiptype-add.component';

describe('ShiptypeAddComponent', () => {
  let component: ShiptypeAddComponent;
  let fixture: ComponentFixture<ShiptypeAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShiptypeAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShiptypeAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
