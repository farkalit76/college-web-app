export interface ITeacher {
    teacherId: number
    firstName: string
    lastName: string
    dateOfBirth: string
    gender: string
    hireDate: string
    address: string
    phoneNumber: string
    email: string
    qualification: string
    experience: number
    subjectExpert: string
    isActive: boolean
  }

  export class TeacherObj {
    firstName: string;
    lastName: string;
    dateOfBirth: string;
    gender: string;
    hireDate: string;
    address: string;
    phoneNumber: string;
    email: string;
    qualification: string;
    experience: number;
    subjectExpert: string;
    isActive: boolean;

    constructor() {
      this.firstName = "";
      this.lastName = "";
      this.dateOfBirth = "";
      this.gender="";
      this.hireDate = "";
      this.address = "";
      this.phoneNumber = "";
      this.email = "";
      this.qualification = "";
      this.experience = 0;
      this.subjectExpert = "";
      this.isActive = true;
  }
}