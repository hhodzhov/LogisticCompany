import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ApiService } from 'src/app/api.service';

@Component({
  selector: 'edit-shipment',
  templateUrl: './edit.shipment.dialog.html',
  styleUrls: [],
})
export class EditShipment implements OnInit {
  constructor(
    private dialogRef: MatDialogRef<EditShipment>,
    private apiService: ApiService,
    @Inject(MAT_DIALOG_DATA) data: any
  ) {
    if (data) {
      this.data = data;
      this.isEdit = true;
    } else {
      this.isEdit = false;
    }
  }

  shipmentStatuses = ['INITIATED', 'SENT', 'ARRIVED', 'DELIVERED'];
  offices: any;
  users: any;

  ngOnInit() {
    this.apiService.loadCompanies().subscribe((data: any) => {
      this.companies = data;
    });
    this.apiService.loadOffices().subscribe((data: any) => {
      this.offices = data;
    });
    this.apiService.loadUsers().subscribe((data: any) => {
        this.users = data;
      });
  }

  isEdit = true;
  data: any = {};

  companies: any;

  update() {
    this.apiService.updateOffice(this.data).subscribe((data: any) => {
      console.log(data);
      this.close();
    });
  }

  delete() {
    this.apiService
      .deleteOffice(this.data.officeName)
      .subscribe((data: any) => {
        console.log(data);
        this.close();
      });
  }

  create() {
    this.apiService.createShipment(this.data).subscribe((data: any) => {
      console.log(data);
      this.close();
    });
  }

  close() {
    this.dialogRef.close();
  }

  cancel() {
    this.close();
  }
}
