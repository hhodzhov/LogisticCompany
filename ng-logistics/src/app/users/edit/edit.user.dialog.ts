import { Component, Inject } from "@angular/core";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material/dialog";
import { ApiService } from "src/app/api.service";

@Component({
    selector: 'edit-user',
    templateUrl: './edit.user.dialog.html',
    styleUrls: []
})
export class EditUserDialog {
    constructor(
        private dialogRef: MatDialogRef<EditUserDialog>,
        private apiService: ApiService,
        @Inject(MAT_DIALOG_DATA) data: any) {
        if (data) {
            this.data = data;
            this.isEdit = true;
        } else {
            this.isEdit = false
        }
    }

    isEdit = true;
    data: any = {};

    editUser() {
        this.apiService.editUser(this.data)
            .subscribe(data => {
                console.log(data);
                this.close();
            });
    }

    create() {
        this.apiService.createUser(this.data)
            .subscribe(data => {
                console.log(data);
                this.close();
            });
    }

    deleteUser() {
        this.apiService.deleteUser(this.data.username)
            .subscribe(data => {
                console.log(data);
                this.close();
            });
    }

    close() {
        this.dialogRef.close();
    }

}