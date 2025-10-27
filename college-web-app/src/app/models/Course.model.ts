export interface ICourse {
    courseId: string
    courseName: string
    subjects: string
    descriptions: string
    credits: number
    isActive: boolean
    cid: number
  }

export class CourseObj {
    courseId: string;
    courseName: string;
    subjects: string;
    descriptions: string;
    credits: number;
    isActive: boolean;

    constructor() {
      this.courseId = "";
      this.courseName = "";
      this.subjects = "";
      this.descriptions = "";
      this.credits = 0;
      this.isActive = true;
  }
}