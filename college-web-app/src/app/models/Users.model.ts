export interface IUsers {
    userId: number
    userName: string
    password: string
    role: number
    status: number
    lastLoginAt: string
    loginAttempts: number
    createdAt: string
    searchUser: string
  }

  export class UsersObj {
    userId: number;
    userName: string;
    password: string;
    role: number;
    status: number;
    lastLoginAt: string;
    loginAttempts: number;
    createdAt: string;
    searchUser: string;

    constructor() {
      this.userId = 0;
      this.userName = "";
      this.password = "";
      this.role = 4;
      this.status = 1;
      this.lastLoginAt = "";
      this.loginAttempts = 0;
      this.createdAt = "";
      this.searchUser = "";
  }
}