import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Priority, Status, Task, TicketType} from "../model/task";
import {Label} from "../model/label";
import {Stats} from "fs";

@Injectable({
  providedIn: 'root'
})
export class DictionaryService {

  private baseUrl = 'https://cbs-task-tracker.herokuapp.com/api';

  constructor(private http: HttpClient) {
  }

  getAllTicketTypes(): Observable<TicketType[]> {
    return this.http.get<TicketType[]>(`${this.baseUrl}/dictionaries/ticketType`);
  }

  getAllPriorities(): Observable<Priority[]> {
    return this.http.get<Priority[]>(`${this.baseUrl}/dictionaries/priority`);
  }

  getAllStatuses(): Observable<Status[]> {
    return this.http.get<Status[]>(`${this.baseUrl}/dictionaries/status`);
  }
}
