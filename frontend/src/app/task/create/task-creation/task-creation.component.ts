import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {Priority, Status, Task, TicketType} from "../../../model/task";
import {User} from "../../../model/user";
import {Label} from "../../../model/label";
import {LabelService} from "../../../service/label.service";
import {ComponentService} from "../../../service/component.service";
import {Components} from "../../../model/components";
import {UserService} from "../../../service/user.service";
import {DictionaryService} from "../../../service/dictionary.service";
import {TicketService} from "../../../service/ticket.service";
import {TaskCreationRequest} from "../../../model/task-creation-request";
import {AuthenticationService} from "../../../service/authentication.service";
import {Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-task-creation',
  templateUrl: './task-creation.component.html',
  styleUrls: ['./task-creation.component.css']
})
export class TaskCreationComponent implements OnInit {

  @ViewChild('closeModal') public closeModal!:ElementRef;

  public task: TaskCreationRequest = {};
  public labels: Label[] = [];
  public components: Components[] = [];
  public users: User[] = [];
  public ticketTypes: TicketType[] = [];
  public priorities: Priority[] = [];

  constructor(private labelService: LabelService,
              private componentService: ComponentService,
              private dictionaryService: DictionaryService,
              private ticketService: TicketService,
              private userService: UserService,
              private router: Router,
              private authService: AuthenticationService) { }

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
  }

  createTask() {
    let currentUserId = this.authService.getCurrentUserId();
    this.task.reporterId = currentUserId;
    this.ticketService.createTask(this.task).subscribe(task => {
      this.task = {}
      this.closeModal.nativeElement.click();
      this.router.navigate(['/task/' + task.id])
    })
  }
}
