import { Component, OnInit } from '@angular/core';
import { FormsModule }   from '@angular/forms';

@Component({
  selector: 'app-game-add',
  templateUrl: './game-add.component.html',
  styleUrls: ['./game-add.component.css']
})
export class GameAddComponent implements OnInit {

  captainsName: string;

  constructor() { }

  ngOnInit() {
  }

  resetModel() {
    this.captainsName = '';
  }
}
