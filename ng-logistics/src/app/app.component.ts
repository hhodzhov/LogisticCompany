import { Component } from '@angular/core';
import { faFilm, faUserCircle, faBuilding, faBriefcase, faTruck } from '@fortawesome/free-solid-svg-icons';
import { AuthService } from './auth/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor(private authService: AuthService) {}

  title = 'ng-logistics';
  filmIcon = faFilm;
  usersIcon = faUserCircle;
  companiesIcon = faBuilding;
  officesIcon = faBriefcase;
  shipmentsIcon = faTruck;

  logout() {
    this.authService.logout();
  }
}
