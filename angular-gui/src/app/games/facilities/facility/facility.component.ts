import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { Facility } from '../../../entities/data_model';
import { FacilitiesService } from '../../../services/facilities.service';

@Component({
  selector: 'app-facility',
  templateUrl: './facility.component.html',
  styleUrls: ['./facility.component.css']
})
export class FacilityComponent implements OnInit {

  gameId: number;
  facility: Facility;

  facilityForm: FacilityForm;

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private facilitiesService: FacilitiesService,
    private fb: FormBuilder
  ) { }

  ngOnInit() {
    this.gameId = +this.route.snapshot.paramMap.get('gameId');
    this.facilitiesService
      .getFacility(this.gameId, +this.route.snapshot.paramMap.get('facilityId'))
      .subscribe(facility => {
        this.facility = facility;
        this.createForm();
      });
  }

  createForm() {
    this.facilityForm = this.fb.group({
      name: [ this.facility.name, Validators.required ],
      constructionCost: this.facility.constructionCost,
      constructionDays: this.facility.constructionDays,
      maintenancePerDay: this.facility.maintenancePerDay,
      workers: this.facility.workers
      // TODO: consumption and production
    });
  }

  onSubmit() {
    const facilityModel = this.facilityForm.value;
    const saveFacility : Facility = {
      id: this.facility.id,
      name: facilityModel.name,
      constructionCost: facilityModel.constructionCost,
      constructionDays: facilityModel.constructionDays,
      maintenancePerDay: facilityModel.maintenancePerDay,
      workers: facilityModel.workers
      // TODO: consumption and production
    };
    this.facilitiesService.updateFacility(this.gameId, saveFacility)
    .subscribe(facility => {});
  }


}
