import { Component, Input, Output, EventEmitter  } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { Good } from '../../../../entities/data_model';
import { GoodsService } from '../../../../services/goods.service';

@Component({
  selector: 'app-good-add',
  templateUrl: './good-add.component.html',
  styleUrls: ['./good-add.component.css']
})
export class GoodAddComponent {

  @Input() gameId: number;
  @Output() onGoodAdded: EventEmitter<Good> = new EventEmitter();

  goodForm: FormGroup;

  constructor(
    private goodsService: GoodsService,
    private fb: FormBuilder
  ) {
    this.createForm();
  }

  createForm() {
    this.goodForm = this.fb.group({
      name: [ null, Validators.required ]
    });
  }

  onSubmit() {
    this.goodsService.addGood(
      this.gameId,
      this.goodForm.get('name').value
    ).subscribe(good => this.onGoodAdded.emit(good));
  }

  onCancel() {
      this.onGoodAdded.emit(null);
    }

}
