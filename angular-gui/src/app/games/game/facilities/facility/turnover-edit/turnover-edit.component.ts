import { Component, Input, Output, EventEmitter } from '@angular/core';
import { FormGroup } from '@angular/forms';

import { Good } from '../../../goods/good';

@Component({
  selector: 'app-turnover-edit',
  templateUrl: './turnover-edit.component.html',
  styleUrls: ['./turnover-edit.component.css']
})
export class TurnoverEditComponent {

  @Input() goods: Good[];
  @Input() turnover: FormGroup;
  @Output() onClear: EventEmitter<any> = new EventEmitter();

  constructor() { }

  clear() {
    this.onClear.emit();
  }
}
