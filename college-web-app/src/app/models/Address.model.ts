export interface IAddress {
    addressId: number
    studentId: string
    localAddress: string
    permanentAdd: string
    permanentCity: string
    permanentState: string
    permanentPIN: string
    phoneNumber: string
    emailId: string
    guardianName: string
    guardianContact: string
    emergencyContact: string
  }

export class AddressObj {
    studentId: string;
    localAddress: string;
    permanentAdd: string;
    permanentCity: string;
    permanentState: string;
    permanentPIN: string;
    phoneNumber: string;
    emailId: string;
    guardianName: string;
    guardianContact: string;
    emergencyContact: string;

    constructor() {
      this.studentId = "";
      this.localAddress = "";
      this.permanentAdd = "";
      this.permanentCity = "";
      this.permanentState = "";
      this.permanentPIN = "";
      this.phoneNumber = "";
      this.emailId = "";
      this.guardianName = "";
      this.guardianContact = "";
      this.emergencyContact = "";
  }
}