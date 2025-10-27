export interface IStudent {
    studentId: string
    firstName: string
    lastName: string
    dob: string
    gender: string
    email: string
    phone: string
    admissionDate: string
    active: boolean
    sid: number
  }

  export class StudentObj {
    studentId: string;
    firstName: string;
    lastName: string;
    dob: string;
    gender: string;
    email: string;
    phone: string;
    admissionDate: string;
    active: boolean;


    constructor() {
      this.studentId = "";
      this.firstName = "";
      this.lastName = "";
      this.dob="";
      this.gender = "";
      this.email = "";
      this.phone = "";
      this.admissionDate = "";
      this.active = true;
  }
}