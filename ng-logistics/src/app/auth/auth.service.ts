import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable()
export class AuthService {
    jwtHelper: JwtHelperService = new JwtHelperService();
    constructor(private router: Router,  private http: HttpClient) {
    }

    public isAuthenticated(): boolean {
        const token: any = localStorage.getItem('token');
        return !this.jwtHelper.isTokenExpired(token);
    }

    public getToken(): String {
        return String(localStorage.getItem('token'));
    }

    public saveToken(token: any) {
        localStorage.setItem("token", token);
    }
    
    public deleteToken() {
        localStorage.removeItem("token");
    }

    public logout() {
        localStorage.removeItem("token");
        this.router.navigate(['']);
    }

    public login(username: any, password: any) {
        this.http.post('http://localhost:8080/api/login', {
            username: username,
            password: password
        }).subscribe((data: any) => {
            this.saveToken(data['access_token'])
            this.router.navigate(['users']);
        })
    }

    public register(username: any, password: any, name: any) {
        this.http.post('http://localhost:8080/api/register-client', {
            username: username,
            password: password,
            name: name
        }).subscribe((_data: any) => {
            this.login(username, password);
        })
    }

}