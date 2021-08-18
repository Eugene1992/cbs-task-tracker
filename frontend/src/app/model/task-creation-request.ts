import {User} from "./user";
import {Components} from "./components";
import {Label} from "./label";

export interface TaskCreationRequest {
  id?: string;
  key?: string;
  title?: string;
  status?: string;
  dueDate?: string;
  description?: string;
  priority?: Priority;
  type?: TicketType;
  assigneeId?: string;
  reporterId?: string;
  components?: string[];
  labels?: string[];
  estimate?: string;
}

export enum Priority {
  BLOCKER,
  CRITICAL,
  MAJOR,
  NORMAL,
  MINOR
}

export enum Status {
  OPEN,
  IN_PROGRESS,
  ON_HOLD,
  IMPLEMENTED,
  CLOSED
}

export enum TicketType {
  STORY,
  DEFECT,
  ACTION_ITEM
}
