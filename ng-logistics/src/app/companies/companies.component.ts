import { Component, OnInit } from "@angular/core";
import { MatDialog, MatDialogConfig } from "@angular/material/dialog";
import { faPlus } from "@fortawesome/free-solid-svg-icons";
import { ApiService } from "../api.service";
import { EditCompany } from "./edit/edit.company.dialog";
import { MapModalComponent } from "./map/map.modal.component";

@Component({
    selector: 'companies',
    templateUrl: './companies.component.html',
    styleUrls: ['./companies.component.css']
})
export class CompaniesComponent implements OnInit {
    createCompanyIcon = faPlus;

    ngOnInit(): void {
        this.reloadCompanies();
    }

    constructor(
        private apiService: ApiService,
        private dialog: MatDialog) {

    }

    companies: any = []

    openEditCompany(company: any) {
        const dialogConfig = new MatDialogConfig();
        dialogConfig.autoFocus = true;
        dialogConfig.data = company;

        let ref = this.dialog.open(EditCompany, dialogConfig);

        ref.afterClosed().subscribe(_result => {
            this.reloadCompanies();
        });
    }

    openMap() {
        this.dialog.open(MapModalComponent);
    }

    reloadCompanies() {
        this.apiService.loadCompanies()
            .subscribe(data => {
                this.companies = data;
                console.log(data);
            });
    }


    createCompany() {
        let ref = this.dialog.open(EditCompany);

        ref.afterClosed().subscribe(_result => {
            this.reloadCompanies();
        });
    }
}