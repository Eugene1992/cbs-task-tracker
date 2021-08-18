import {User} from "./user";
import {Components} from "./components";
import {Label} from "./label";

export interface Task {
  id?: string;
  key?: string;
  title?: string;
  description?: string;
  priority?: Priority;
  status?: Status;
  type?: TicketType;
  assignee?: User;
  reporter?: User;
  components?: Components[];
  labels?: Label[];
  watchers?: User[];
  estimate?: number;
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
