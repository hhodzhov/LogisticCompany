import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable()
export class AuthService {
  jwtHelper: JwtHelperService = new JwtHelperService();
  constructor(private router: Router, private http: HttpClient) {}

  public isAuthenticated(): boolean {
    const token: any = localStorage.getItem('token');
    return !this.jwtHelper.isTokenExpired(token);
  }

  public getToken() {
    return String(localStorage.getItem('token'));
  }

  public saveToken(token: any) {
    localStorage.setItem('token', token);
  }

  public deleteToken() {
    localStorage.removeItem('token');
  }

  public logout() {
    localStorage.removeItem('token');
    location.reload();
  }

  public login(username: any, password: any) {
    this.http
      .post('http://localhost:8080/api/login', {
        username: username,
        password: password,
      })
      .subscribe((data: any) => {
        this.saveToken(data['access_token']);
        this.router.navigate(['users']);
      });
  }

  public register(username: any, password: any, name: any) {
    this.http
      .post('http://localhost:8080/api/public/user/create', {
        username: username,
        password: password,
        name: name,
      })
      .subscribe((_data: any) => {
        this.login(username, password);
      });
  }

  public getRoles() {
    if (!this.isAuthenticated()) {
      return [];
    }
    let tokenDecoded: any = this.jwtHelper.decodeToken(this.getToken());
    return tokenDecoded.roles;
  }

  public hasRole(roleName: any) {
    return this.getRoles().includes(roleName);
  }

  public getUsername() {
    if (!this.isAuthenticated()) {
      return [];
    }
    let tokenDecoded: any = this.jwtHelper.decodeToken(this.getToken());

    return tokenDecoded.sub;
  }
}
