import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { AuthService } from "./auth/auth.service";

@Injectable()
export class ApiService {
    private baseUrl = 'http://localhost:8080';

    constructor(
        private http: HttpClient,
        private authService: AuthService) {
    }

    // USERS

    loadUsers() {
        return this.http.get<any>(`${this.baseUrl}/api/admin/users`, this.requestOptions());
    }

    deleteUser(username: any) {
        return this.http.delete<any>(`${this.baseUrl}/api/admin/user/${username}/delete`, this.requestOptions());
    }

    editUser(body: any) {
        return this.http.post<any>(`${this.baseUrl}/api/admin/user/update`, body, this.requestOptions());
    }

    createUser(body: any) {
        return this.http.post<any>(`${this.baseUrl}/api/public/user/create`, body, this.requestOptions());
    }

    // OFFICES

    loadOffices() {
        return this.http.get<any>(`${this.baseUrl}/api/admin/offices`, this.requestOptions());
    }

    createOffice(body: any) {
        return this.http.post<any>(`${this.baseUrl}/api/admin/office/create`, body, this.requestOptions());
    }

    updateOffice(body: any) {
        return this.http.post<any>(`${this.baseUrl}/api/admin/office/update`, body, this.requestOptions());
    }

    deleteOffice(officeName: any) {
        return this.http.delete<any>(`${this.baseUrl}/api/admin/offices/${officeName}/delete`, this.requestOptions());
    }


    // Companies

    loadCompanies() {
        return this.http.get<any>(`${this.baseUrl}/api/admin/logistic-companies`, this.requestOptions());
    }

    createCompany(body: any) {
        return this.http.post<any>(`${this.baseUrl}/api/admin/logistic-company/create`, body,  this.requestOptions());
    }

    updateCompany(body: any) {
        return this.http.post<any>(`${this.baseUrl}/api/admin/logistic-company/update`, body, this.requestOptions());
    }

    deleteCompany(name: any) {
        return this.http.delete<any>(`${this.baseUrl}/api/admin/logistic-company/${name}/delete`, this.requestOptions());
    }

    // Shipments

    loadShipments() {
        return this.http.get<any>(`${this.baseUrl}/api/agent/shipments`, this.requestOptions());
    }


    createShipment(body: any) {
        return this.http.post<any>(`${this.baseUrl}/api/agent/shipment/create`, body, this.requestOptions());
    }

    updateShipment(body: any, id :any) {
        return this.http.post<any>(`${this.baseUrl}/api/agent/shipment/${id}/update`, body, this.requestOptions());
    }

    deleteShipment(id: any) {
        return this.http.delete<any>(`${this.baseUrl}/api/agent/shipment/${id}/delete`, this.requestOptions());
    }


    private requestOptions() {
        let token = this.authService.getToken();
        let header = {
            Authorization: `Bearer ${token}`
        }
        return {
            headers: new HttpHeaders(header),
        };
    }
}