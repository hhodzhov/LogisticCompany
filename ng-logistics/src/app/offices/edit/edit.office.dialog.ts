import { Component, Inject } from "@angular/core";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material/dialog";
import { ApiService } from "src/app/api.service";
@Component({
    selector: 'edit-company',
    templateUrl: './edit.office.dialog.html',
    styleUrls: []
})
export class EditOffice {
    constructor(
        private dialogRef: MatDialogRef<EditOffice>,
        private apiService: ApiService,
        @Inject(MAT_DIALOG_DATA) data: any) {
        if (data) {
            this.data = data;
            this.data.logisticCompanyId = 1;
            this.isEdit = true;
        }
        else {
            this.isEdit = false;
        }
    }

    isEdit = true;
    data: any = {}

    update() {
        this.apiService.updateOffice(this.data)
            .subscribe(data => {
                console.log(data);
                this.close();
            })
    }

    delete() {
        this.apiService.deleteOffice(this.data.officeName)
            .subscribe(data => {
                console.log(data);
                this.close();
            })
    }

    create() {
        this.apiService.createOffice(this.data)
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