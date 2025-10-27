import { Routes } from '@angular/router';
import { Student } from './view/student/student';
import { StudentAddress } from './view/student-address/student-address';
import { StudentAdmission } from './view/student-admission/student-admission';
import { FeePayment } from './view/fee-payment/fee-payment';
import { Semester } from './view/semester/semester';
import { Course } from './view/course/course';
import { AboutUs } from './view/about-us/about-us';
import { Examination } from './view/examination/examination';
import { ExamResult } from './view/exam-result/exam-result';
import { Users } from './view/users/users';
import { Login } from './view/login/login';

export const routes: Routes = [

    {
        path: '',
        redirectTo: '/login-page',
        pathMatch: 'full'
    },
    {
        path: 'student-page',
        component: Student
    },
    {
        path: 'address-page',
        component: StudentAddress
    },
    {
        path: 'address-page/:id',
        component: StudentAddress
    },
    {
        path: 'admission-page',
        component: StudentAdmission
    },
    {
        path: 'admission-page/:id',
        component: StudentAdmission
    },
    {
        path: 'payment-page',
        component: FeePayment
    },
    {
        path: 'payment-page/:id/:courseId',
        component: FeePayment
    },
    {
        path: 'semester-page',
        component: Semester
    },
    {
        path: 'course-page',
        component: Course
    },
    {
        path: 'examination-page',
        component: Examination
    },
    {
        path: 'result-page',
        component: ExamResult
    },
    {
        path: 'users-page',
        component: Users
    },
    {
        path: 'login-page',
        component: Login
    },
    {
        path: 'about-page',
        component: AboutUs
    }

];
