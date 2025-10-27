1. Log4j is added with logback.xml file. It will print console output in XML format. It also includes traceId.
2. Swagger API, with proper api comments.
3. JWT security for API authentication and authorization.
4. SonarQube and SonarList implementation for Java coding standard.
5. Junit test cases with Mockito to test the API. Test coverage should be > 80%.
6. JMeter verified for Load test. Gatling API is also used for Load test.
7. Spring boot 3.3.4 with Hibernate and JpaRepository, security (of header value) implementation.
8. Java JDBC Connection with Hikari connection pool size and its idle size.
9. Each API calls print its elapsed time whenever it is called. It prints as TOTAL_EXECUTION_TIME_IS : in ms


Log4j prints with Trace Id as:
------------------------------
In Console/TXT Format:
2024-08-12 17:06:05,370 INFO [http-nio-8080-exec-2] o.u.t.a.UsmanController [UsmanController.java:54] TraceId: 13F900C00744473780189E99C00E986C - usman controller employees started
And in XML Format:
{"@timestamp":"2024-08-12T17:06:05.3704866+05:30","@version":"1","message":"usman controller employees started","logger_name":"apis.org.usman.api.trans.UsmanController","thread_name":"http-nio-8080-exec-2","level":"INFO","level_value":20000,"id":"13F900C00744473780189E99C00E986C"}

Elapsed time taken by method prints with Log as:
-----------------------------------------------
{"@timestamp":"2024-08-12T17:26:18.0799162+05:30","@version":"1","message":"API_CLASS Name : apis.org.usman.api.trans.UsmanController, Method Name:getEmployeeById, TOTAL_EXECUTION_TIME_IS :6   ms ","logger_name":"config.common.org.usman.api.trans.LogExecutionTimeAdvice","thread_name":"http-nio-8080-exec-2","level":"INFO","level_value":20000,"id":"1F01755F1CB749BA832DC6B7AD53F85D"}


----------------------------------------
Application is divided into two sections
-----------------------------------------
Admin Controller → manage Students, Courses, Admissions, Fees, Semesters, Subjects, Exams, Results.

User Controller → student self-service: view profile, enrolled courses, payments, results.

Possible Business Scenarios Covered

Admin (college staff):
------------------------------
    Add/update/delete students
    Manage courses
    Enroll students in courses (Admissions)
    Record fee structures & payments
    Schedule semesters & exams
    Record exam results

User (student portal):
-------------------------------
    View personal profile
    Check admission/enrollment status
    View fee payment history & dues
    See semester exams and results
