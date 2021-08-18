import { Component, OnInit } from '@angular/core';
import {Label} from "../model/label";
import {Components} from "../model/components";
import {User} from "../model/user";
import {Priority, Status, Task, TicketType} from "../model/task";
import {LabelService} from "../service/label.service";
import {ComponentService} from "../service/component.service";
import {DictionaryService} from "../service/dictionary.service";
import {TicketService} from "../service/ticket.service";
import {UserService} from "../service/user.service";
import {Router} from "@angular/router";
import {AuthenticationService} from "../service/authentication.service";
import {TaskCreationRequest} from "../model/task-creation-request";
import {ProjectService} from "../service/project.service";
import {Project} from "../model/project";
import {TaskSearchRequest} from "../model/task-search-request";

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  public request: TaskSearchRequest = {};

  public labels: Label[] = [];
  public components: Components[] = [];
  public users: User[] = [];
  public ticketTypes: TicketType[] = [];
  public priorities: Priority[] = [];
  public ticketStatuses: Status[] = [];
  public projects: Project[] = [];

  public tickets: Task[] = [];

  constructor(private labelService: LabelService,
              private componentService: ComponentService,
              private dictionaryService: DictionaryService,
              private ticketService: TicketService,
              private projectService: ProjectService,
              private userService: UserService) { }

  ngOnInit(): void {
    this.labelService.getAllLabels().subscribe((labels: Label[]) => {
      this.labels = labels;
    })
    this.componentService.getAllComponents().subscribe((components: Components[]) => {
      this.components = components;
    })
    this.userService.getAllUsers().subscribe((users: User[]) => {
      this.users = users;
    })
    this.dictionaryService.getAllPriorities().subscribe((priorities: Priority[]) => {
      this.priorities = priorities;
    })
    this.dictionaryService.getAllTicketTypes().subscribe((ticketTypes: TicketType[]) => {
      this.ticketTypes = ticketTypes;
    })
    this.dictionaryService.getAllStatuses().subscribe((statuses: Status[]) => {
      this.ticketStatuses = statuses;
    })
    this.projectService.getAllProjects().subscribe((projects: Project[]) => {
      this.projects = projects;
    })
    this.ticketService.searchTickets(this.request).subscribe(tickets => {
      this.tickets = tickets;
    })
  }

  onProjectFilterChange(projectId: string) {
    if (!projectId) {
      this.request.projectId = undefined;
    }
    this.request.projectId = projectId;
    this.reloadTickets();
  }

  onTypeFilterChange(type: string) {
    this.request.type = type;
    this.reloadTickets();
  }

  reloadTickets() {
    this.ticketService.searchTickets(this.request).subscribe(tickets => {
      this.tickets = tickets;
    })
  }

  onStatusFilterChange(status: string) {
    this.request.status = status;
    this.reloadTickets();
  }

  onPriorityFilterChange(priority: string) {
    this.request.priority = priority;
    this.reloadTickets();
  }

  onAssigneeFilterChange(assigneeId: string) {
    this.request.assigneeId = assigneeId;
    this.reloadTickets();
  }

  onReporterFilterChange(reporterId: string) {
    this.request.reporterId = reporterId;
    this.reloadTickets();
  }
}
