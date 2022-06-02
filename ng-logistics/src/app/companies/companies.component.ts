import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Component, OnInit } from "@angular/core";
import { MatDialog, MatDialogConfig } from "@angular/material/dialog";
import { faPlus } from "@fortawesome/free-solid-svg-icons";
import { AuthService } from "../auth/auth.service";
import { CreateCompany } from "./create/create.company.dialog";
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
        private authService: AuthService,
        private http: HttpClient,
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
        let token = this.authService.getToken();
        let header = {
            Authorization: `Bearer ${token}`
        }
        const requestOptions: any = {
            headers: new HttpHeaders(header),
        };
        this.http.get<any>('http://localhost:8080/api/admin/logistic-companies', requestOptions)
            .subscribe(data => {
                this.companies = data;
                console.log(data);
            })
    }


    createCompany() {
        let ref = this.dialog.open(CreateCompany);

        ref.afterClosed().subscribe(_result => {
            this.reloadCompanies();
        });
    }
}