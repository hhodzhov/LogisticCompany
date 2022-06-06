import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { faPlus } from '@fortawesome/free-solid-svg-icons';
import { ApiService } from '../api.service';
import { AuthService } from '../auth/auth.service';
import { EditShipment } from './edit/edit.shipment.dialog';

@Component({
  selector: 'shipments',
  templateUrl: './shipments.component.html',
  styleUrls: ['./shipments.component.css'],
})
export class ShipmentsComponent implements OnInit {
  createCompanyIcon = faPlus;
  constructor(
    private apiService: ApiService,
    private dialog: MatDialog,
    private authServie: AuthService
  ) {}

  shipments: any;

  reloadShipments() {
    this.apiService.loadShipments().subscribe((data: any) => {
      console.log(data);
      if (this.isClient()) {
        let username = this.authServie.getUsername();
        let tmp = [];
        for (let s of data) {
          if (s.senderName === username || s.recipientName == username) {
            tmp.push(s);
          }
        }
        this.shipments = tmp;
      } else {
        this.shipments = data;
      }
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
    if (!this.canCreateNew()) {
      return;
    }
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.data = shipment;

    let ref = this.dialog.open(EditShipment, dialogConfig);

    ref.afterClosed().subscribe((result: any) => {
      this.reloadShipments();
    });
  }

  isClient() {
    return this.authServie.getRoles().length == 1 && this.authServie.getRoles().includes('CLIENT')
  }

  canCreateNew() {
    return this.authServie.hasRole('ADMIN');
  }
}
