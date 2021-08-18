import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs/operators";
import jwtDecode from 'jwt-decode';
import Token from "../model/token";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  isUser!: boolean;
  isAdmin!: boolean;

  constructor(
    private httpClient: HttpClient,
    private router: Router
  ) {
  }

  authenticate(login: string, password: string) {
    return this.httpClient.post<any>('https://cbs-task-tracker.herokuapp.com/auth', {login, password}).pipe(
      map(
        userData => {
          sessionStorage.setItem('username', login);
          let token = 'Bearer ' + userData.token;
          sessionStorage.setItem('token', token);
          let decodedToken: Token = jwtDecode<Token>(token);
          let role = decodedToken.roles[0].name;
          sessionStorage.setItem('role', role);
          sessionStorage.setItem('userId', decodedToken.userId);

          this.isUser = role === "ROLE_USER";
          this.isAdmin = role === "ROLE_ADMIN";

          this.router.navigate(['/']);

          return userData;
        }
      )
    );
  }


  isUserLoggedIn() {
    let user = sessionStorage.getItem('username')
    //console.log(!(user === null))
    return !(user === null)
  }

  isUserLogged(): boolean {
    return this.isUser;
  }

  isAdminLogged(): boolean {
    return this.isUser;
  }

  logOut() {
    sessionStorage.removeItem('username')
    sessionStorage.removeItem('token')
    sessionStorage.removeItem('role')
    sessionStorage.removeItem('userId')
  }

  getCurrentUserId(): string {
    return sessionStorage.getItem('userId') || "";
  }
}
