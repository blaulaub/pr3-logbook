import { Component, OnInit, Input } from '@angular/core';

import { ImportExportService} from './import-export.service';

@Component({
  selector: 'app-import-export',
  templateUrl: './import-export.component.html',
  styleUrls: ['./import-export.component.css']
})
export class ImportExportComponent implements OnInit {

  @Input() gameId: number;

  constructor(
    private importExportService: ImportExportService
  ) { }

  ngOnInit() {
  }

  fileChange(e: any) {
    let file = e.target.files[0];
    let reader = new FileReader();
    reader.onload = it => this.importExportService.upload(this.gameId, reader.result as String);
    reader.readAsText(file);
  }

}
