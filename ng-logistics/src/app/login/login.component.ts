import { Component, OnInit } from "@angular/core";
import { MatDialog } from "@angular/material/dialog";
import { ActivatedRoute } from "@angular/router";
import { LoginDialog } from "./login.dialog";
import { RegisterDialog } from "./register.dialog";


@Component({
    selector: 'login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
    constructor(public dialog: MatDialog, private route: ActivatedRoute) {
    }

    ngOnInit(): void {
        this.route.queryParams
            .subscribe((params: any) => {
                this.openDialog(params['register'] === 'true');
            }
            );
    }

    private openDialog(register: boolean) {
        if (register) {
            let dialogRef = this.dialog.open(RegisterDialog, {});
        } else {
            let dialogRef = this.dialog.open(LoginDialog, {});
        }
    }
}