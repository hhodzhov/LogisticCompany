import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Component, OnInit } from "@angular/core";
import { MatDialog, MatDialogConfig } from "@angular/material/dialog";
import { faPlus } from "@fortawesome/free-solid-svg-icons";
import { AuthService } from "../auth/auth.service";
import { CreateOffice } from "./create/create.shipment.dialog";
import { EditOffice } from "./edit/edit.office.dialog";

@Component({
    selector: 'shipments',
    templateUrl: './shipments.component.html',
    styleUrls: ['./shipments.component.css']
})
export class ShipmentsComponent implements OnInit {
    createCompanyIcon = faPlus;

    ngOnInit(): void {
        this.reload();
    }

    constructor(
        private authService: AuthService,
        private http: HttpClient,
        private dialog: MatDialog) {

    }

    offices: any = []

    edit(office: any) {
        const dialogConfig = new MatDialogConfig();
        dialogConfig.autoFocus = true;
        dialogConfig.data = office;

        let ref = this.dialog.open(EditOffice, dialogConfig);

        ref.afterClosed().subscribe(_result => {
            this.reload();
        });
    }

    reload() {
        let token = this.authService.getToken();
        let header = {
            Authorization: `Bearer ${token}`
        }
        const requestOptions: any = {
            headers: new HttpHeaders(header),
        };
        this.http.get<any>('http://localhost:8080/api/admin/offices', requestOptions)
            .subscribe(data => {
                this.offices = data;
                console.log(data);
            })
    }


    create() {
        let ref = this.dialog.open(CreateOffice);

        ref.afterClosed().subscribe(_result => {
            this.reload();
        });
    }
}