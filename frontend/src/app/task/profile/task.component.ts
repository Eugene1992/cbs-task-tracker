import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Subscription} from "rxjs";
import {TicketService} from "../../service/ticket.service";
import {Task} from "../../model/task";
import {AuthenticationService} from "../../service/authentication.service";
import {UserService} from "../../service/user.service";

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {

  private taskId: any;
  public task: Task = {};
  public isCurrentUserWatcher: boolean = false;
  public watchersCount: number = 0;
  public currentUserId!: string;

  constructor(private route: ActivatedRoute,
              private taskService: TicketService,
              private userService: UserService,
              private authService: AuthenticationService) {
  }

  ngOnInit(): void {
    this.currentUserId = this.authService.getCurrentUserId();
    this.route.params.subscribe(params => {
      this.taskId = params['id'];
      this.taskService.getTask(this.taskId).subscribe((task: Task) => {
        this.task = task;
        if (task.watchers) {
          this.watchersCount = task.watchers.length;
          this.isCurrentUserWatcher = !task.watchers.find(watcher => watcher.id === this.currentUserId)
        }
      });
    });
  }


  watchTicket() {
    this.userService.watchTicket(this.currentUserId, this.task.id!).subscribe(() => {
      this.isCurrentUserWatcher = true;
      this.taskService.getTicketWatchersCount(this.taskId).subscribe((watchersCount: number) => {
        this.watchersCount = watchersCount;
      });
    });
  }

  stopWatchTicket() {
    this.userService.stopWatchTicket(this.currentUserId, this.task.id!).subscribe(() => {
      this.isCurrentUserWatcher = false;
      this.taskService.getTicketWatchersCount(this.taskId).subscribe((watchersCount: number) => {
        this.watchersCount = watchersCount;
      });
    });
  }
}
