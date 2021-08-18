import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Task} from "../model/task";
import {Label} from "../model/label";
import {User} from "../model/user";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = 'https://cbs-task-tracker.herokuapp.com/api';

  constructor(private http: HttpClient) {
  }

  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.baseUrl}/users`);
  }

  watchTicket(userId: string, ticketId: string): Observable<void> {
    return this.http.post<void>(`${this.baseUrl}/users/${userId}/tickets/${ticketId}/watch`, null);
  }

  stopWatchTicket(userId: string, ticketId: string): Observable<void> {
    return this.http.post<void>(`${this.baseUrl}/users/${userId}/tickets/${ticketId}/stopWatch`, null);
  }
}
