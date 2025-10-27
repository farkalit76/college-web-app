Java 17 with Spring boot 3.2.2
-------------------------------

1. Log4j is added with logback.xml file. It will print console output in XML format. It also includes traceId.
2. Swagger API, with proper api comments.
3. JWT security for API authentication and authorization.
4. SonarQube and SonarList implementation for Java coding standard.
5. Junit test cases with Mockito to test the API. Test coverage should be > 80%.
6. JMeter verified for Load test. Gatling API is also used for Load test.
7. Spring boot 3.2.2 with Hibernate and JpaRepository implementation.
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


Run and Test the Application
-----------------------------
1. Start the application.
2. Goto to postman and run the below API.
   curl --location 'http://localhost:8090/api/v1/auth-management/authenticate' \
   --header 'Content-Type: application/json' \
   --data-raw '{
   "email": "jamil@test.com",
   "password": "1234"
   }'
3. You will get the JWT token and you can use this token to proceed your applicaiton.
4. To validate the token, run the below API.
   curl --location 'http://localhost:8090/api/v1/auth-management/authenticate/token' \
   --header 'Content-Type: application/json' \
   --data-raw '{
   "userEmail": "jamil@test.com",
   "token": "eyJhbGciOiJIUzI1NiJ9.eyJhY2NvdW50X2lkIjoiMTEwMDExIiwiY29tcGFueV9pZCI6IjEyMzQ1IiwidXNlcl9pZCI6IjIyMDA5OSIsImlzc3VlX2J5IjoicWl3YSIsInVzZXJfcm9sZXMiOlsiVVNFUiIsIkFETUlOIl0sInN1YiI6ImphbWlsQHRlc3QuY29tIiwiaXNzIjoiaW5mb0B1c21hbi10dXRvcmlhbC5jb20iLCJpYXQiOjE3MzEyOTkzODUsImV4cCI6MTczMTMzNTM4NX0.cq5piRBMJUrzatvUkfpWF3r9ihLUwQLG5MMzp7wSvaE"
   }'
5. To Create new users, run the below API.
