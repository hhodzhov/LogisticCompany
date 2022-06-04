import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { faPlus } from '@fortawesome/free-solid-svg-icons';
import { ApiService } from '../api.service';
import { EditShipment } from './edit/edit.shipment.dialog';

@Component({
  selector: 'shipments',
  templateUrl: './shipments.component.html',
  styleUrls: ['./shipments.component.css'],
})
export class ShipmentsComponent {
  createCompanyIcon = faPlus;
  constructor(private apiService: ApiService, private dialog: MatDialog) {}

  shipments = [];

  reloadShipments() {
    this.apiService.loadShipments().subscribe((data: any) => {
      console.log(data);
      this.shipments = data;
    });
  }


  createShipment() {
    let ref = this.dialog.open(EditShipment);

    ref.afterClosed().subscribe((_result: any) => {
        this.reloadShipments();
    });
  }
}
