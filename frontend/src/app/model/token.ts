import {Role} from "./role";

export default interface Token {
  roles: Role[];
  sub: string;
  userId: string;
}
