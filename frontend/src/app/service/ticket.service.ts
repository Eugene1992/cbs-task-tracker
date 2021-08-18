import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Task} from "../model/task";
import {TaskCreationRequest} from "../model/task-creation-request";
import {TaskSearchRequest} from "../model/task-search-request";

@Injectable({
  providedIn: 'root'
})
export class TicketService {

  private baseUrl = 'https://cbs-task-tracker.herokuapp.com/api';

  constructor(private http: HttpClient) {
  }

  getTask(id: number): Observable<Task> {
    return this.http.get<Task>(`${this.baseUrl}/tickets/${id}`);
  }

  createTask(task: TaskCreationRequest): Observable<Task> {
    return this.http.post<Task>(`${this.baseUrl}/tickets`, task);
  }

  getTicketWatchersCount(id: number): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/tickets/${id}/watchers/count`);
  }

  searchTickets(request: TaskSearchRequest): Observable<Task[]> {
    return this.http.post<Task[]>(`${this.baseUrl}/tickets/search`, request);
  }
}
