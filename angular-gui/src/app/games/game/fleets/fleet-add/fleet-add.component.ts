import { Component, Input, Output, EventEmitter  } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { Fleet } from '../../../../entities/data_model';
import { FleetsService } from '../../../../services/fleets.service';

@Component({
  selector: 'app-fleet-add',
  templateUrl: './fleet-add.component.html',
  styleUrls: ['./fleet-add.component.css']
})
export class FleetAddComponent {

  @Input() gameId: number;
  @Output() onFleetAdded: EventEmitter<Fleet> = new EventEmitter();

  fleetForm: FormGroup;

  constructor(
    private fleetsService: FleetsService,
    private fb: FormBuilder
  ) {
    this.createForm();
  }

  createForm() {
    this.fleetForm = this.fb.group({
      name: [ null, Validators.required ]
    });
  }

  onSubmit() {
    this.fleetsService.addFleet(
      this.gameId,
      this.fleetForm.get('name').value
    ).subscribe(fleet => this.onFleetAdded.emit(fleet));
  }

  onCancel() {
      this.onFleetAdded.emit(null);
    }

}
