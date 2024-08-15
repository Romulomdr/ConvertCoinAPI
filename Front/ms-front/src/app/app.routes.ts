import { Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { ListUsersComponent } from './pages/list-users/list-users.component';
import { BalanceComponent } from './pages/balance/balance.component';

export const routes: Routes = [
  {
    path: "login",
    component: LoginComponent
  },
  {
    path: "list-users",
    component: ListUsersComponent
  },
  {
    path: "balance",
    component: BalanceComponent
  }
];
