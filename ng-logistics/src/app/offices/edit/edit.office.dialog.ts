import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ApiService } from 'src/app/api.service';

@Component({
  selector: 'edit-company',
  templateUrl: './edit.office.dialog.html',
  styleUrls: [],
})
export class EditOffice implements OnInit{
  constructor(
    private dialogRef: MatDialogRef<EditOffice>,
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

  ngOnInit() {
    this.apiService.loadCompanies().subscribe((data: any) => {
        console.log(data);
        this.companies = data;
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
    this.apiService.createOffice(this.data).subscribe((data: any) => {
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
