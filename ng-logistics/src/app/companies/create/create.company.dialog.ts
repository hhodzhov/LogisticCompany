import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Component, Inject } from "@angular/core";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material/dialog";
import { AuthService } from "src/app/auth/auth.service";

@Component({
    selector: 'create-company',
    templateUrl: './create.company.dialog.html',
    styleUrls: []
})
export class CreateCompany {
    constructor(
        public dialogRef: MatDialogRef<CreateCompany>,
        @Inject(MAT_DIALOG_DATA) data: any,
        private authService: AuthService,
        private http: HttpClient) {

        if (data) {
            console.log(data);
            this.name = data.name;
            this.country = data.country;
            this.address = data.centralOfficeAddress;
            this.city = data.city;
        } else {
            this.isUpdate = false;
        }
    }

    isUpdate = true;

    name: any = '';
    country: any = '';
    city: any = '';
    address: any = '';

    updateName(name: any) {
        this.name = name;
    }

    updateCountry(country: any) {
        this.country = country;
    }

    updateCity(city: any) {
        this.city = city;
    }

    updateAddress(address: any) {
        this.address = address;
    }

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

    close() {
        this.dialogRef.close();
    }

    cancel() {

    }


}