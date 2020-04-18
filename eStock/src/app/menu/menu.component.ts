import { Component, OnInit } from '@angular/core';
import { UserService} from '../service/user.service';
@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {
name  = '';
annee = 0;
  constructor( public userService : UserService) { }

  ngOnInit(): void {
    this.name = localStorage.getItem('name');
    this.annee = parseInt(localStorage.getItem('annee'));

  }
  public logout() {
    localStorage.removeItem('name');
    location.reload();
  }
}
