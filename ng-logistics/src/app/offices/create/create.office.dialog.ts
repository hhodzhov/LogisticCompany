import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Component, Inject } from "@angular/core";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material/dialog";
import { AuthService } from "src/app/auth/auth.service";

@Component({
    selector: 'edit-company',
    templateUrl: './edit.office.dialog.html',
    styleUrls: []
})
export class EditOffice {
    constructor(
        public dialogRef: MatDialogRef<EditOffice>,
        @Inject(MAT_DIALOG_DATA) data: any,
        private authService: AuthService,
        private http: HttpClient) {

        console.log(data);
        this.city = data.city;
        this.officeName = data.officeName;
        this.address = data.address;
        this.logisticCompanyId = data.logisticCompanyId;
    }


    city: any = '';
    officeName: any = '';
    address: any = '';
    logisticCompanyId: any = ''

    create() {
        let token = this.authService.getToken();
        let header = {
            Authorization: `Bearer ${token}`
        }
        const requestOptions: any = {
            headers: new HttpHeaders(header),
        };

        let body = {
            name: this.name,
            country: this.country,
            city: this.city,
            centralOfficeAddress: this.address
        }

        this.http.post<any>('http://localhost:8080/api/admin/logistic-company/create', body, requestOptions)
            .subscribe(data => {
                console.log(data);
                this.close();
            })
    }

    updateOfficeName(officeName: any) {
        this.officeName = officeName;
    }

    updateAddress(address: any) {
        this.address = address;
    }

    updateCity(city: any) {
        this.city = city;
    }


    close() {
        this.dialogRef.close();
    }

    cancel() {
        this.close();
    }


}