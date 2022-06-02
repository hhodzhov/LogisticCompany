import { Component } from "@angular/core";
import { MatDialogRef } from "@angular/material/dialog";
import { AuthService } from "../auth/auth.service";

@Component({
    selector: 'register-dialog',
    templateUrl: './register.dialog.html',
    styleUrls: ['./register.dialog.css']
})
export class RegisterDialog {
    constructor(
        public dialogRef: MatDialogRef<RegisterDialog>,
        private authService: AuthService) { }
    username = "";
    password = "";
    name = "";

    updateUsername(username: any) {
        this.username = username;
    }

    updatePassword(password: any) {
        this.password = password;
    }

    updateName(name: any) {
        this.name = name;
    }


    register() {
        this.authService.register(this.username, this.password, this.name);
        this.dialogRef.close();
    }
}