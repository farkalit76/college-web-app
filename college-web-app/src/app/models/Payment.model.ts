export interface IPayment {
    paymentId: number
    studentId: string
    courseId: string
    academicYear: string
    feePaid: number
    penaltyPaid: number
    paymentDate: string
    paymentMode: string
    referenceNumber: string
  }
  
export class PaymentObj {
    studentId: string;
    courseId: string;
    academicYear: string;
    feePaid: number | null = null;
    penaltyPaid: number | null = null;
    paymentDate: string;
    paymentMode: string;
    referenceNumber: string;

    constructor() {
      this.studentId = "";
      this.courseId = "";
      this.academicYear = "";
      this.feePaid = 0;
      this.penaltyPaid = 0;
      this.paymentDate = "";
      this.paymentMode = "";
      this.referenceNumber = "";
  }
}
