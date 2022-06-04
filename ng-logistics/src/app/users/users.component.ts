import { Component, OnInit } from "@angular/core";
import { MatDialog, MatDialogConfig } from "@angular/material/dialog";
import { faPlus } from "@fortawesome/free-solid-svg-icons";
import { ApiService } from "../api.service";
import { EditUserDialog } from "./edit/edit.user.dialog";

@Component({
  selector: 'users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {
  createUserIcon = faPlus;

  constructor(
    private apiService: ApiService,
    private dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.reloadUsers();

  }
  users: any = []

  private reloadUsers() {
    this.apiService.loadUsers()
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

  createUser() {
    let ref = this.dialog.open(EditUserDialog);

    ref.afterClosed().subscribe(_result => {
      this.reloadUsers();
    });
  }

}
