import { Component, Input, Output, EventEmitter  } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { Facility } from '../../../../entities/data_model';
import { FacilitiesService } from '../../../../services/facilities.service';

@Component({
  selector: 'app-facility-add',
  templateUrl: './facility-add.component.html',
  styleUrls: ['./facility-add.component.css']
})
export class FacilityAddComponent {

  @Input() gameId: number;
  @Output() onFacilityAdded: EventEmitter<Facility> = new EventEmitter();

  facilityForm: FormGroup;

  constructor(
    private facilitiesService: FacilitiesService,
    private fb: FormBuilder
  ) {
    this.createForm();
  }

  createForm() {
    this.facilityForm = this.fb.group({
      name: [ null, Validators.required ]
    });
  }

  onSubmit() {
    this.facilitiesService.addFacility(
      this.gameId,
      this.facilityForm.get('name').value
    ).subscribe(facility => this.onFacilityAdded.emit(facility));
  }

  onCancel() {
      this.onFacilityAdded.emit(null);
    }

}
