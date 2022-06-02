import { Component } from "@angular/core";
import { MatDialogRef } from "@angular/material/dialog";


@Component({
  selector: 'map-component',
  templateUrl: './map.modal.component.html',
  styleUrls: []
})
export class MapModalComponent {
  constructor(public dialogRef: MatDialogRef<MapModalComponent>) { }

  closeDialog() {
    this.dialogRef.close('Pizza!');
  }
}