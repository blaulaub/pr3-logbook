import { Component, Input, Output, EventEmitter  } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { Shiptype } from '../shiptype';
import { ShiptypesService } from '../shiptypes.service';

@Component({
  selector: 'app-shiptype-add',
  templateUrl: './shiptype-add.component.html',
  styleUrls: ['./shiptype-add.component.css']
})
export class ShiptypeAddComponent {

  @Input() gameId: number;
  @Output() onShiptypeAdded: EventEmitter<Shiptype> = new EventEmitter();

  shiptypeForm: FormGroup;

  constructor(
    private shiptypesService: ShiptypesService,
    private fb: FormBuilder
  ) {
    this.createForm();
  }

  createForm() {
    this.shiptypeForm = this.fb.group({
      name: [ null, Validators.required ]
    });
  }

  onSubmit() {
    this.shiptypesService.addShiptype(
      this.gameId,
      this.shiptypeForm.get('name').value
    ).subscribe(shiptype => this.onShiptypeAdded.emit(shiptype));
  }

  onCancel() {
      this.onShiptypeAdded.emit(null);
    }

}
