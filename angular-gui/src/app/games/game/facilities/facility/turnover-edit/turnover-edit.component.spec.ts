import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TurnoverEditComponent } from './turnover-edit.component';

describe('TurnoverEditComponent', () => {
  let component: TurnoverEditComponent;
  let fixture: ComponentFixture<TurnoverEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TurnoverEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TurnoverEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
