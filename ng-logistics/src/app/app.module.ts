import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome'
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
import { OfficesComponent } from './offices/offices.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { AppRoutingModule } from './app-routing.module';
import { ApiService } from './api.service';
import { EditCompany } from './companies/edit/edit.company.dialog';
import { EditOffice } from './offices/edit/edit.office.dialog';
import { EditUserDialog } from './users/edit/edit.user.dialog';
import { ShipmentsComponent } from './shipments/shipments.components';
import { EditShipment } from './shipments/edit/edit.shipment.dialog';


@NgModule({
  declarations: [
    AppComponent,
    UsersComponent,
    LoginComponent,
    CompaniesComponent,
    OfficesComponent,
    ShipmentsComponent,
    EditCompany,
    EditOffice,
    EditUserDialog,
    EditShipment
  ],
  imports: [
    CommonModule,
    AppRoutingModule,
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
    MatFormFieldModule,
  ],
  providers: [
    AuthGuardService,
    AuthService,
    ApiService
  ],
  entryComponents: [
    EditCompany,
    EditOffice,
    EditUserDialog,
    EditShipment],
  bootstrap: [AppComponent]
})
export class AppModule { }
