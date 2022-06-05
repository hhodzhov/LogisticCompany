import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ApiService } from 'src/app/api.service';

@Component({
  selector: 'edit-user',
  templateUrl: './edit.user.dialog.html',
  styleUrls: [],
})
export class EditUserDialog {
  constructor(
    private dialogRef: MatDialogRef<EditUserDialog>,
    private apiService: ApiService,
    @Inject(MAT_DIALOG_DATA) data: any
  ) {
    if (data) {
      this.data = data;
      this.isEdit = true;

      for (let role of data.roles) {
        this.roles[role.name] = true;
      }
    } else {
      this.isEdit = false;
    }
  }

  roles: any = {
    ADMIN: false,
    AGENT: false,
    COURIER: false,
    CLIENT: false,
  };

  isEdit = true;
  data: any = {};

  editUser() {
    this.apiService.editUser(this.data).subscribe((data: any) => {
      console.log(data);
      this.close();
    });
  }

  create() {
    this.apiService.createUser(this.data).subscribe((data: any) => {
      console.log(data);
      this.close();
    });
  }

  deleteUser() {
    this.apiService.deleteUser(this.data.username).subscribe((data: any) => {
      console.log(data);
      this.close();
    });
  }

  close() {
    this.dialogRef.close();
  }

  toggleRole(roleName: any) {
    if (this.roles[roleName]) {
      this.removeRole(roleName);
    } else {
      this.addRole(roleName);
    }
  }

  private removeRole(roleName: any) {
    this.apiService
      .removeRole({
        userName: this.data.username,
        roleName: roleName,
      })
      .subscribe((data: any) => console.log(data));
  }

  private addRole(roleName: any) {
    this.apiService
      .addRole({
        userName: this.data.username,
        roleName: roleName,
      })
      .subscribe((data: any) => console.log(data));
  }
}
