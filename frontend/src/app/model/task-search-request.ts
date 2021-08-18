import {Priority, TicketType} from "./task";

export interface TaskSearchRequest {
  status?: string;
  priority?: string;
  type?: string;
  assigneeId?: string;
  reporterId?: string;
  projectId?: string;
}
