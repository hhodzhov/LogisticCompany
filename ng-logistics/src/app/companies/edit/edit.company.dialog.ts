import { Component, Inject } from "@angular/core";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material/dialog";
import { ApiService } from "src/app/api.service";

@Component({
    selector: 'edit-company',
    templateUrl: './edit.company.dialog.html',
    styleUrls: []
})
export class EditCompany {
    constructor(
        public dialogRef: MatDialogRef<EditCompany>,
        @Inject(MAT_DIALOG_DATA) data: any,
        private apiService: ApiService) {

        if (data) {
            console.log(data);
            this.data = data;
            this.isEdit = true;
        } else {
            this.isEdit = false;
        }
    }

    isEdit = true;
    data: any = {};

    updateCompany() {
        this.apiService.updateCompany(this.data)
            .subscribe(data => {
                console.log(data);
                this.close();
            });
    }

    create() {
        this.apiService.createCompany(this.data)
            .subscribe(data => {
                console.log(data);
                this.close();
            })
    }

    delete() {
        this.apiService.deleteCompany(this.data.name)
            .subscribe(data => {
                console.log(data);
                this.close();
            })
    }

    close() {
        this.dialogRef.close();
    }

    cancel() {
        this.close();
    }


}