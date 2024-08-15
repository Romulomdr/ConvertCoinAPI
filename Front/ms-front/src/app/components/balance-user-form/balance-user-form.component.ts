import { Component } from '@angular/core';
import { NgFor } from '@angular/common';
import { Router } from '@angular/router';

interface User {
  userId: string;
  name: string;
  email: string;
  moeda: string;
  saldo: number;
}

@Component({
  selector: 'app-balance-user-form',
  standalone: true,
  imports: [NgFor],
  templateUrl: './balance-user-form.component.html',
  styleUrl: './balance-user-form.component.scss'
})
export class BalanceUserFormComponent {
  users: User[] = [];

  constructor(private router: Router) {}

  goToLogin(){
    this.router.navigate(['/login']);
  }
  getUsers(){

  }

}
