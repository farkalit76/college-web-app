My Task: I want to create a BE and FE application with:
1. BE - Spring boot 3.3.4 APIs, security, swagger and PostgreSQL Database.
   a. Two APIs, one for POST login and anouther for get all students (without DB)
   b. security will use header with key/value. It will permit the login API and other API will be authenticated.
2. FE - Angular 20.0 web application with HTML CSS.
   -One Login page with username & password: It will call a POST REST API. 
    Once success it will open another age with student APIs. If fail it will remain on login page.


3. Command for Angular application
   
  ng new angular-auth-demo --standalone false --routing true --style css

  cd angular-auth-demo
  
  ng generate component login
  
  ng generate component students
  
  ng generate service auth
