import { Component, OnInit } from "@angular/core";
import { MatDialog } from "@angular/material/dialog";
import { ActivatedRoute } from "@angular/router";
import { LoginDialog } from "./login.dialog";
import { RegisterDialog } from "./register.dialog";


@Component({
    selector: 'login',
    template: '',
    styleUrls: []
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
            this.dialog.open(RegisterDialog, {});
        } else {
            this.dialog.open(LoginDialog, {});
        }
    }
}