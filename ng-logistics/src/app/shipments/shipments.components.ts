import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { faPlus } from '@fortawesome/free-solid-svg-icons';
import { ApiService } from '../api.service';
import { EditShipment } from './edit/edit.shipment.dialog';

@Component({
  selector: 'shipments',
  templateUrl: './shipments.component.html',
  styleUrls: ['./shipments.component.css'],
})
export class ShipmentsComponent implements OnInit {
  createCompanyIcon = faPlus;
  constructor(private apiService: ApiService, private dialog: MatDialog) {}

  shipments: any;

  reloadShipments() {
    this.apiService.loadShipments().subscribe((data: any) => {
      console.log(data);
      this.shipments = data;
    });
  }

  ngOnInit() {
    this.reloadShipments();
  }

  createShipment() {
    let ref = this.dialog.open(EditShipment);

    ref.afterClosed().subscribe((_result: any) => {
      this.reloadShipments();
    });
  }

  openEditShipment(shipment: any) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.data = shipment;

    let ref = this.dialog.open(EditShipment, dialogConfig);

    ref.afterClosed().subscribe((result: any) => {
        this.reloadShipments();
    });
  }
}
