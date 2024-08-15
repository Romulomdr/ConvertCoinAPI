import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { NgFor } from '@angular/common';
import { Router } from '@angular/router';
import { tap } from 'rxjs/operators';

interface User {
  userId: string;
  name: string;
  email: string;
  moeda: string;
  saldo: number;
}

@Component({
  selector: 'app-get-users-form',
  standalone: true,
  imports: [NgFor],
  templateUrl: './get-users-form.component.html',
  styleUrl: './get-users-form.component.scss'
})

export class GetUsersFormComponent implements OnInit{

  constructor(private http: HttpClient, private router: Router) {}

  users: User[] = [];

  ngOnInit() {
    this.getUsers();
  }

  getUsers() {
    this.http.get<User[]>('http://localhost:8081/users')
      .pipe(
        tap(response => {
          this.users = response;
        })
      )
      .subscribe({
        error: error => {
          console.error('Erro ao buscar usuários:', error);
        }
      });
    }

  goToLogin(){
    this.router.navigate(['/login']);
  }
}
