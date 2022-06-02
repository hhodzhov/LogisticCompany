import { Component } from "@angular/core";
import { MatDialogRef } from "@angular/material/dialog";
import { AuthService } from "../auth/auth.service";

@Component({
    selector: 'login-dialog',
    templateUrl: './login.dialog.html',
    styleUrls: ['./login.dialog.css']
})
export class LoginDialog {
    constructor(
        public dialogRef: MatDialogRef<LoginDialog>,
        private authService: AuthService) {
    }

    username: any = "";
    password: any = "";

    signIn() {
        this.authService.login(this.username, this.password);
        this.dialogRef.close();
    }


    updatePassword(password: any) {
        this.password = password;
    }

    updateUsername(username: any) {
        this.username = username;
    }
}