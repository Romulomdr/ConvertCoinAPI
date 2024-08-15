import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { tap } from 'rxjs/operators';

@Component({
  selector: 'app-create-user-form',
  standalone: true,
  imports: [
    FormsModule,
  ],
  templateUrl: './create-user-form.component.html',
  styleUrl: './create-user-form.component.scss'
})
export class CreateUserFormComponent {
  user = {
    name: '',
    email: '',
    moeda: '',
    saldo: 0.00

  };

  constructor(private http: HttpClient, private router: Router) {}

  onSubmit() {
    //vendo se resolve o problema do double null
    this.user.saldo = parseFloat(this.user.saldo.toString());

    console.log('Usuário cadastrado:', this.user);

    this.http.post('http://localhost:8081/users', this.user)
    .pipe(
      tap(response => {
        console.log('Resposta do Servidor: ', response);
      })
    )
    .subscribe({
      error: error => {
        console.error('Erro ao cadastrar usuário: ', error);
      }
    });
  }
  goToRegisteredUsers(){
    this.router.navigate(['/list-users']);
  }
  goToBalance(){
    this.router.navigate(['/balance']);
  }
}
