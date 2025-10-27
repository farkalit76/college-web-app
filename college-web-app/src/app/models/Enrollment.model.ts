export interface IEnrollment {
    enrollmentId: string
    studentId: string
    courseId: string
    enrollmentDate: string
    isPaid: boolean
    paymentId: number
    isActive: boolean
    eid: number
  }
  
export class EnrollmentObj {
    studentId: string;
    courseId: string;
    enrollmentDate: string;
    isPaid: boolean;
    paymentId: number;
    isActive: boolean;

    constructor() {
      this.studentId = "";
      this.courseId = "";
      this.enrollmentDate = "";
      this.isPaid = false;
      this.paymentId = 0;
      this.isActive = false;
  } 
}