import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-game-date',
  templateUrl: './game-date.component.html',
  styleUrls: ['./game-date.component.css']
})
export class GameDateComponent implements OnInit {

  @Input() date: Date;
  @Output() onDateChanged: EventEmitter<Date> = new EventEmitter();

  editOn: boolean;
  dateEdit: Date;
  match: any;

  constructor() { }

  ngOnInit() {
  }

  incDate() {
    this.date = new Date(new Date(this.date).getTime() + (1000 * 60 * 60 * 24));
    this.onDateChanged.emit(this.date);
  }

  decDate() {
    this.date = new Date(new Date(this.date).getTime() - (1000 * 60 * 60 * 24));
    this.onDateChanged.emit(this.date);
  }

  switchToEdit() {
    this.dateEdit = this.date;
    this.editOn = true;
  }

  acceptEdit() {
    this.date = new Date(Date.UTC(this.match[3], this.match[2]-1, this.match[1], 0, 0, 0, 0));
    this.editOn = false;
    this.onDateChanged.emit(this.date);
  }

  cancelEdit() {
    this.editOn = false;
  }

  matchDate(value: string) {
    this.match = value.match(/^(\d+).(\d+).(\d+)$/);
  }

}
