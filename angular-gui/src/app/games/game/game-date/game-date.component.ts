import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-game-date',
  templateUrl: './game-date.component.html',
  styleUrls: ['./game-date.component.css']
})
export class GameDateComponent implements OnInit {

  editOn: boolean;
  date: Date;
  dateEdit: Date;
  match: any;

  constructor() { }

  ngOnInit() {
    this.date = new Date("1550-01-01");
  }

  incDate() {
    this.date = new Date(this.date.getTime() + (1000 * 60 * 60 * 24));
  }

  decDate() {
    this.date = new Date(this.date.getTime() - (1000 * 60 * 60 * 24));
  }

  switchToEdit() {
    this.dateEdit = this.date;
    this.editOn = true;
  }

  acceptEdit() {
    this.date = new Date(this.match[3], this.match[2]-1, this.match[1]);
    this.editOn = false;
  }

  cancelEdit() {
    this.editOn = false;
  }

  matchDate(value: string) {
    this.match = value.match(/^(\d+).(\d+).(\d+)$/);
  }

}
