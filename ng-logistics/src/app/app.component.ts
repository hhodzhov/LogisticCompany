import { Component } from '@angular/core';
import {
  faFilm,
  faUserCircle,
  faBuilding,
  faBriefcase,
  faTruck,
  faChartBar,
  faArrowRight
} from '@fortawesome/free-solid-svg-icons';
import { AuthService } from './auth/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  constructor(private authService: AuthService) {}

  title = 'ng-logistics';
  filmIcon = faFilm;
  usersIcon = faUserCircle;
  companiesIcon = faBuilding;
  officesIcon = faBriefcase;
  shipmentsIcon = faTruck;
  referencesIcon = faChartBar;
  bulletIcon = faArrowRight;

  hasRole(roleName: any) {
    return this.authService.getRoles().includes(roleName);
  }

  logout() {
    this.authService.logout();
  }
}
