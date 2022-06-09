import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { AuthGuardService } from './auth/auth.guard';
import { CompaniesComponent } from './companies/companies.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { OfficesComponent } from './offices/offices.component';
import { ReferencesComponent } from './references/references.component';
import { ShipmentsComponent } from './shipments/shipments.components';
import { UsersComponent } from './users/users.component';

const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'home',
    component: HomeComponent,
    canActivate: [AuthGuardService]
  },
  {
    path: '',
    component: AppComponent,
    canActivate: [AuthGuardService]
  },
  {
    path: 'users',
    component: UsersComponent,
    canActivate: [AuthGuardService]
  },
  {
    path: 'companies',
    component: CompaniesComponent,
    canActivate: [AuthGuardService]
  },
  {
    path: 'offices',
    component: OfficesComponent,
    canActivate: [AuthGuardService]
  },
  {
    path: 'shipments',
    component: ShipmentsComponent,
    canActivate: [AuthGuardService]
  },
  {
    path: 'references/:refId',
    component: ReferencesComponent,
    canActivate: [AuthGuardService]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
