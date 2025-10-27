Insert Sample Data
================================================================

--1. Students
INSERT INTO students (student_id, first_name, last_name, dob, gender, email, phone, admission_date, createdBy)
VALUES
('STU001', 'Ahmad', 'Usmani', '2002-05-12', 'Male', 'ahamd@gmail.com', '9876543210', '2024-07-15', 'admin'),
('STU002', 'Pervez', 'Alam', '2003-02-20', 'Female', 'pervez@gmail.com', '9123456780', '2024-07-16', 'admin'),
('STU003', 'Riyaz', 'Akhtar', '2001-11-05', 'Male', 'riyaz@gmail.com', '9988776655', '2024-07-18', 'admin');

--1.2 Student Address
INSERT INTO student_address (student_id, address_line1, address_line2, city, state, postal_code, createdBy)
VALUES
('STU001', '123 MG Road', 'Near Park', 'Delhi', 'Delhi', '110001', 'admin'),
('STU002', '45 Residency Lane', NULL, 'Delhi', 'Delhi', '110001', 'admin'),
('STU003', '78 Civil Lines', 'Flat 5B', 'Delhi', 'Delhi', '110001', 'admin');

--2. Courses
INSERT INTO courses (course_name, course_code, duration_months, createdBy)
VALUES
('Bachelor of Computer Science', 'BCS101', 36, 'admin'),
('Bachelor of Commerce', 'BCOM102', 36, 'admin'),
('Bachelor of Arts', 'BA103', 36, 'admin');

--3. Student Admissions
INSERT INTO student_admissions (admission_id, student_id, course_code, admission_year, status, createdBy)
VALUES
('ADM001', 'STU001', 'BCS101', 2024, 'Active', 'admin'),
('ADM002', 'STU002', 'BCOM102', 2024, 'Active', 'admin'),
('ADM003', 'STU003', 'BA103', 2024, 'Active', 'admin');

--4. Fee Structure & Payments
INSERT INTO fee_structure (course_code, total_fee, createdBy)
VALUES
('BCS101', 150000.00, 'admin'),
('BCOM102', 120000.00, 'admin'),
('BA103', 100000.00, 'admin');

INSERT INTO fee_payments (admission_id, installment_no, amount_paid, payment_date, payment_mode, createdBy)
VALUES
('ADM001', 1, 50000.00, '2024-07-20', 'BankTransfer', 'admin'),
('ADM001', 2, 25000.00, '2024-08-20', 'Cash', 'admin'),
('ADM002', 1, 60000.00, '2024-07-25', 'Card', 'admin');

5. Semesters
INSERT INTO semesters (course_code, semester_no, start_date, end_date, createdBy)
VALUES
('BCS101', 1, '2024-08-01', '2025-01-31', 'admin'),
('BCOM102', 1, '2024-08-01', '2025-01-31', 'admin'),
('BA103', 1, '2024-08-01', '2025-01-31', 'admin');

--6. Examinations
INSERT INTO examinations (semester_id, exam_name, exam_date, createdBy)
VALUES
(1, 'BCS101 - Semester 1 Final', '2025-01-20', 'admin'),
(2, 'BCOM102 - Semester 1 Final', '2025-01-22', 'admin'),
(3, 'BA103 - Semester 1 Final', '2025-01-25', 'admin');

--7. Subjects

--Changed reference: course_code VARCHAR(20) REFERENCES courses(course_code)

INSERT INTO subjects (course_code, subject_name, subject_code, createdBy)
VALUES
('BCS101', 'Data Structures', 'CS101', 'admin'),
('BCS101', 'Database Systems', 'CS102', 'admin'),
('BCOM102', 'Financial Accounting', 'COM101', 'admin'),
('BA103', 'History of India', 'BA101', 'admin');

--8. Exam Results
INSERT INTO exam_results (exam_id, student_id, subject_id, marks_obtained, grade, createdBy)
VALUES
(1, 'STU001', 1, 85.50, 'A', 'admin'),
(1, 'STU001', 2, 78.00, 'B+', 'admin'),
(2, 'STU002', 3, 88.00, 'A', 'admin'),
(3, 'STU003', 4, 67.00, 'C+', 'admin');

INSERT INTO users (username, password, role)
VALUES
('admin', '$2a$10$ABCDEF1234567890abcdef1234567890abcdef1234567890abcdef', 1),
('john', '$2a$10$XYZ987654321abcdef1234567890abcdef1234567890abcdef1234', 2),
('user', '$2a$10$XYZ987654321abcdef1234567890abcdef1234567890abcdef1234', 3);




------------------------
INSERT INTO public.courses ("version", courseid, coursename, subjects, descriptions, credits, isactive,
createdat, createdby, modifiedat, modifiedby)
values
( 0, 'CR-100', 'VI', 'Maths, Science, English, SST', 'For class sixth', 10, true, NOW(), 'usman', NOW(), ''),
( 0, 'CR-101', 'IX', 'Maths, Science, English, SST', 'For Matric Borad', 10, true, NOW(), 'usman', NOW(), ''),
( 0, 'CR-102', 'X', 'Maths, Science, English, SST', 'For Matric Borad', 10, true, NOW(), 'usman', NOW(), ''),
( 0, 'CR-103', 'XI', 'SST, English, History, Hindi', 'For Arts', 10, true, NOW(), 'usman', NOW(), ''),
( 0, 'CR-104', 'XI', 'Maths, Economics, English, Hindi', 'For Commerce', 10, true, NOW(), 'usman', NOW(), ''),
( 0, 'CR-105', 'XI', 'Maths, Physics, Chemistry, English', 'For Science with Maths', 10, true, NOW(), 'usman', NOW(), ''),
( 0, 'CR-106', 'XI', 'Biology, Physics,Chemistry, English', 'For Science with Biology', 10, true, NOW(), 'usman', NOW(),'');

