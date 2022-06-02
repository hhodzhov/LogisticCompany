import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Component, Inject, OnInit } from "@angular/core";
import { MatDialog, MatDialogConfig, MAT_DIALOG_DATA } from "@angular/material/dialog";
import { AuthService } from "../auth/auth.service";
import { EditUserDialog } from "./edit/edit.user.dialog";

@Component({
  selector: 'users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {
  constructor(
    private http: HttpClient,
    private authService: AuthService,
    private dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.reloadUsers();

  }
  users: any = []

  private reloadUsers() {
    let token = this.authService.getToken();
    let header = {
      Authorization: `Bearer ${token}`
    }
    const requestOptions: any = {
      headers: new HttpHeaders(header),
    };
    this.http.get<any>('http://localhost:8080/api/admin/users', requestOptions)
      .subscribe(data => {
        this.users = data;
        console.log(data);
      })
  }


  openEditUser(user: any) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.data = user;

    let ref = this.dialog.open(EditUserDialog, dialogConfig);

    ref.afterClosed().subscribe(_result => {
      this.reloadUsers();
    });
  }

}
