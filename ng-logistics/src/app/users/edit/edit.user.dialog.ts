import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Component, Inject } from "@angular/core";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material/dialog";
import { AuthService } from "src/app/auth/auth.service";

@Component({
    selector: 'edit-user',
    templateUrl: './edit.user.dialog.html',
    styleUrls: ['./edit.user.dialog.css']
})
export class EditUserDialog {
    constructor(
        private http: HttpClient,
        private authService: AuthService,
        private dialogRef: MatDialogRef<EditUserDialog>,
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


    editUser() {
        let token = this.authService.getToken();
        let header = {
            Authorization: `Bearer ${token}`
        }
        const requestOptions: any = {
            headers: new HttpHeaders(header),
        };

        let body = {
            name: this.name,
            username: this.username,
            password: this.password
        }

        this.http.post<any>('http://localhost:8080/api/admin/user/update', body, requestOptions)
            .subscribe(data => {
                console.log(data);
                this.close();
            })
    }


    deleteUser() {
        let token = this.authService.getToken();
        let header = {
            Authorization: `Bearer ${token}`
        }
        const requestOptions: any = {
            headers: new HttpHeaders(header),
        };

        this.http.delete<any>(`http://localhost:8080/api/admin/user/${this.username}/delete`, requestOptions)
            .subscribe(data => {
                console.log(data);
                this.close();
            })
    }


    close() {
        this.dialogRef.close();
    }

}