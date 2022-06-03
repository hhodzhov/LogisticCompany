import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Component, Inject } from "@angular/core";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material/dialog";
import { AuthService } from "../../auth/auth.service";

@Component({
    selector: 'create-users',
    templateUrl: './create.users.dialog.html',
    styleUrls: []
})
export class CreateUser {
    constructor(
        private http: HttpClient,
        private authService: AuthService,
        private dialogRef: MatDialogRef<CreateUser>,
        @Inject(MAT_DIALOG_DATA) data: any) {
        console.log(data);
        this.username = data['username'];
        this.password = data['password'];
        this.name = data['name']
    }

    username = '';
    name = '';
    password = '';

    updateUsername(username: any) {
        this.username = username;
    }

    updateName(name: any) {
        this.name = name;
    }

    updatePassword(password: any) {
        this.password = password;
    }

    close() {
        this.dialogRef.close();
    }

    cancel() {

    }
}