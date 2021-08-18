import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from "../service/authentication.service";
import {WebsocketService} from "../service/websocket.service";
import {Notification} from "../model/notification";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  isLoggedIn!: boolean;
  isUser!: boolean;
  isAdmin!: boolean;
  notifications: Notification[] = [];

  constructor(private authService: AuthenticationService,
              private toastr: ToastrService,
              private wsService: WebsocketService) { }

  ngOnInit(): void {
    this.isLoggedIn = this.authService.isUserLoggedIn();
    this.isUser = this.authService.isUser;
    this.isAdmin = this.authService.isAdmin;

    let currentUserId = this.authService.getCurrentUserId();

    this.wsService._subscribe('/user/' + currentUserId + '/notifications', (event: any) => {
      this.toastr.success(event.body);
      console.log(event);
      this.notifications.push({text: event.body})
    })

  }

  logout() {
    this.authService.logOut();
  }

  send() {
    this.wsService._send('sdsds');
  }
}
