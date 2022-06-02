import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome'
import { AppRoutingModule } from 'src/app/app-routing.module';
import { UsersComponent } from 'src/app/users/users.component';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { AuthGuardService } from './auth/auth.guard';
import { AuthService } from './auth/auth.service';
import { LoginComponent } from './login/login.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

// import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { MatCardModule } from '@angular/material/card';
import { MatChipsModule } from '@angular/material/chips';
import { MatDialogModule } from "@angular/material/dialog";
import { MatInputModule } from "@angular/material/input";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { MatIconModule } from "@angular/material/icon";
import { CommonModule } from '@angular/common';
import { CompaniesComponent } from './companies/companies.component';



@NgModule({
  declarations: [
    AppComponent,
    UsersComponent,
    LoginComponent,
    CompaniesComponent
  ],
  imports: [
    AppRoutingModule,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserModule,
    FontAwesomeModule,
    RouterModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatIconModule,
    MatChipsModule,
    MatInputModule,
    MatDialogModule,
  ],
  providers: [
    AuthGuardService,
    AuthService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
