import { Component } from '@angular/core';
import { CreateUserFormComponent } from '../../components/create-user-form/create-user-form.component';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    CreateUserFormComponent,
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

}
