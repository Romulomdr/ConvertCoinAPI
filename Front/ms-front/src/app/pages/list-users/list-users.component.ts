import { Component } from '@angular/core';
import { GetUsersFormComponent } from '../../components/get-users-form/get-users-form.component';

@Component({
  selector: 'app-list-users',
  standalone: true,
  imports: [GetUsersFormComponent],
  templateUrl: './list-users.component.html',
  styleUrl: './list-users.component.scss'
})
export class ListUsersComponent {

}
