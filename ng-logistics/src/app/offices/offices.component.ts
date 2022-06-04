import { Component, OnInit } from "@angular/core";
import { MatDialog, MatDialogConfig } from "@angular/material/dialog";
import { faPlus } from "@fortawesome/free-solid-svg-icons";
import { ApiService } from "../api.service";
import { EditOffice } from "./edit/edit.office.dialog";

@Component({
    selector: 'offices',
    templateUrl: './offices.component.html',
    styleUrls: ['./offices.component.css']
})
export class OfficesComponent implements OnInit {
    createCompanyIcon = faPlus;

    ngOnInit(): void {
        this.reloadOffices();
    }

    constructor(
        private apiService: ApiService,
        private dialog: MatDialog) {
    }

    offices: any = []

    openEditOffices(office: any) {
        const dialogConfig = new MatDialogConfig();
        dialogConfig.autoFocus = true;
        dialogConfig.data = office;

        let ref = this.dialog.open(EditOffice, dialogConfig);

        ref.afterClosed().subscribe(_result => {
            this.reloadOffices();
        });
    }

    reloadOffices() {
        this.apiService.loadOffices()
            .subscribe(data => {
                this.offices = data;
                console.log(data);
            });
    }

    createOffice() {
        let ref = this.dialog.open(EditOffice);

        ref.afterClosed().subscribe(_result => {
            this.reloadOffices();
        });
    }
}