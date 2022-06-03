import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Component, Inject } from "@angular/core";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material/dialog";
import { AuthService } from "src/app/auth/auth.service";

@Component({
    selector: 'edit-shipment',
    templateUrl: './edit.shipment.dialog.html',
    styleUrls: []
})
export class EditShipment {
    constructor(
        public dialogRef: MatDialogRef<EditShipment>,
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

    update() {
        let token = this.authService.getToken();
        let header = {
            Authorization: `Bearer ${token}`
        }
        const requestOptions: any = {
            headers: new HttpHeaders(header),
        };

        let body = {
            city: this.city,
            officeName: this.officeName,
            address: this.address,
            logisticCompanyId: this.logisticCompanyId
        }

        this.http.post<any>('http://localhost:8080/api/admin/office/update', body, requestOptions)
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

    delete() {
        let token = this.authService.getToken();
        let header = {
            Authorization: `Bearer ${token}`
        }
        const requestOptions: any = {
            headers: new HttpHeaders(header),
        };

        this.http.delete<any>(`http://localhost:8080/api/admin/offices/${this.officeName}/delete`, requestOptions)
            .subscribe(data => {
                console.log(data);
                this.close();
            })
    }

    close() {
        this.dialogRef.close();
    }

    cancel() {
        this.close();
    }


}