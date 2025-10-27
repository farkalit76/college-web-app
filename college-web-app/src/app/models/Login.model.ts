export interface ILogin {
    userName: string
    password: string
    validation: boolean
  }

  export class LoginObj {
    userName: string;
    password: string;
    validation : boolean;

    constructor() {
      this.userName = "";
      this.password = "";
      this.validation = false;
  }
}