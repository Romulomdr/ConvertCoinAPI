import { Component } from '@angular/core';
import { BalanceUserFormComponent } from '../../components/balance-user-form/balance-user-form.component';

@Component({
  selector: 'app-balance',
  standalone: true,
  imports: [BalanceUserFormComponent],
  templateUrl: './balance.component.html',
  styleUrl: './balance.component.scss'
})
export class BalanceComponent {

}
