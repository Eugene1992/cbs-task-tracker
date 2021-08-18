import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Task} from "../model/task";
import {Label} from "../model/label";
import {Components} from "../model/components";

@Injectable({
  providedIn: 'root'
})
export class ComponentService {

  private baseUrl = 'https://cbs-task-tracker.herokuapp.com/api';

  constructor(private http: HttpClient) {
  }

  getAllComponents(): Observable<Components[]> {
    return this.http.get<Components[]>(`${this.baseUrl}/components`);
  }
}
