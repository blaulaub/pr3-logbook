import { Component, Input, Output, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';

import { Good } from '../../../../entities/data_model';

@Component({
  selector: 'app-turnover-edit',
  templateUrl: './turnover-edit.component.html',
  styleUrls: ['./turnover-edit.component.css']
})
export class TurnoverEditComponent implements OnInit {

  @Input() goods: Good[];
  @Input() turnover: FormGroup;

  constructor() { }

  ngOnInit() {
  }

}
