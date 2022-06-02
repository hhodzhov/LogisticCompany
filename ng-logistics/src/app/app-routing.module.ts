import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { AuthGuardService } from './auth/auth.guard';
import { CompaniesComponent } from './companies/companies.component';
import { LoginComponent } from './login/login.component';
import { UsersComponent } from './users/users.component';

const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent
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
    component: UsersComponent,
    canActivate: [AuthGuardService]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
