import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Task} from "../model/task";
import {Label} from "../model/label";

@Injectable({
  providedIn: 'root'
})
export class LabelService {

  private baseUrl = 'https://cbs-task-tracker.herokuapp.com/api';

  constructor(private http: HttpClient) {
  }

  getAllLabels(): Observable<Label[]> {
    return this.http.get<Label[]>(`${this.baseUrl}/labels`);
  }
}
