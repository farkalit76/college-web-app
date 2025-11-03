Create Custom user storage provider (Using Java 17 with Hibernate)
--------------------------------------------------------------------
Before proceeding to development download Keycloak keycloak-26.4.1
1. Create new realm=usmanrealm, client=usmanclient and some Roles etc. (leave user creation right now)
2. Now Create new Schema and Table inside postgres Database.

CREATE DATABASE collegedb;

CREATE USER college WITH PASSWORD 'college@001';

GRANT ALL PRIVILEGES ON DATABASE collegedb TO college;

ALTER DATABASE collegedb OWNER TO college;

Now-> Try new connection for this new schema with new Users.

Then in schema/connection create new Table with  below SQL.

drop table users; // this table is mapped with MyUserEntity pojo.

CREATE TABLE users (
    id              VARCHAR(10) PRIMARY KEY,          
    username        VARCHAR(50) NOT NULL UNIQUE,      
    password        VARCHAR(255) NOT NULL,            
    email           VARCHAR(50),       			      
    phone           VARCHAR(20),
    firstName       VARCHAR(50),
    lastName        VARCHAR(50)
);

insert into users values('101','test01','$2a$10$DWlKfv1yNywysS9YYpwVl.dATfDo/c4Cd/94LQ4N3uUKqVXbzeDne', 'test01@gmail.com', '9900112233','test', 'test' );
insert into users values('102','test02','$2a$10$DWlKfv1yNywysS9YYpwVl.dATfDo/c4Cd/94LQ4N3uUKqVXbzeDne', 'test02@gmail.com', '9900112222', 'test', 'test');
insert into users values('103','dev01', '$2a$10$DWlKfv1yNywysS9YYpwVl.dATfDo/c4Cd/94LQ4N3uUKqVXbzeDne',  'dev01@gmail.com', '9900112223', 'test', 'test');

select * from users;

3. Now take this code and build it "mvn clean install" then take the jar and deploy under /providers folder
   Also includes the following jars inside the providers 

    -commons-dbcp2-2.13.0
    -commons-pool2-2.12.0
    -jandex-2.4.3.Final
    -jbcrypt-0.4
    -jbcrypt-0.4-sources
    -postgresql-42.7.8
Optional:
    To print log you may put these code into keycolok.conf file:
    log-level= info, org.keycloak.services.resources.admin:debug, org.keycloak.authentication:debug, org.keycloak.services.resources.admin:debug,com.usman.keycloak:debug

    To connect external DB you may add the below code inside keyclock.conf file including /persistence.xml file (otherwise this file is not required):
    db-kind-user-store=postgres
    db-url-user-store=jdbc:postgresql://localhost:5432/collegedb
    db-username-user-store=college
    db-password-user-store=college@001
   
5. Then use command to deploy it: "kc.bat build" after this run: "kc.bat start-dev" 
6. Execute the curl as Request:

curl --location 'http://localhost:8080/realms/usmanrealm/protocol/openid-connect/token' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'client_id=usmanclient' \
--data-urlencode 'client_secret=xqfe5ddhQcm0xuyjzGXMDfD2KLFoLJ6i' \
--data-urlencode 'grant_type=password' \
--data-urlencode 'username=dev01' \
--data-urlencode 'password=password'

You will get the Response:
{
    "access_token": "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJHN3hTV0VhSmxuQkYtem9vbXh1MWhZYUZIdUZiUWFNQl9LaldMUjF2Qnh3In0.eyJleHAiOjE3NjIxNzEzNjksImlhdCI6MTc2MjE3MTA2OSwianRpIjoib25ydHJvOjA2NDkxMDRlLTA3MzctM2QzMS05ZThmLTM3MGFmNzYyZmJhOSIsImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODA4MC9yZWFsbXMvdXNtYW5yZWFsbSIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiJmOjEyOWE1MDVjLWY3ODQtNGNmMS1iMjMwLTViOTg3MzMzNjUyMzoyMjU2MCIsInR5cCI6IkJlYXJlciIsImF6cCI6InVzbWFuY2xpZW50Iiwic2lkIjoiNGFmMWRiYzctZDE4YS0yYmRkLTNkZDItYTI3ZDY5MWI0YjcyIiwiYWNyIjoiMSIsInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJSTF9BRE1JTiIsIlJMX01BTkFHRVIiLCJkZWZhdWx0LXJvbGVzLXVzbWFucmVhbG0iLCJvZmZsaW5lX2FjY2VzcyIsIlJMX0NVU1RPTUVSIiwidW1hX2F1dGhvcml6YXRpb24iXX0sInJlc291cmNlX2FjY2VzcyI6eyJ1c21hbmNsaWVudCI6eyJyb2xlcyI6WyJESVNQQVRDSCIsIk1BTkFHRVIiLCJBRE1JTiIsIlVTRVIiLCJWSUVXIl19LCJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6InByb2ZpbGUgZW1haWwgcGhvbmUiLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwibmFtZSI6InRlc3QgdGVzdHMiLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJkZXYwMSIsImdpdmVuX25hbWUiOiJ0ZXN0IiwiZmFtaWx5X25hbWUiOiJ0ZXN0cyIsImVtYWlsIjoiZGV2MDFAZ21haWwuY29tIn0.QqopZ2E89f4F11FSENt5DkCEYzw2OLMZdmU_uyR7pOtQhXqsh3ab94XDgO8GMBkBMHLO7rDIGVrejPuqF_6L42qN1PXhyxB4crFhtS5lnOy43GCIXAcV5ibygil05pg7cSdRUylNYddH2onEGlrhsZkrojxb1SF0r5enZ0Zfin6IAm66Y3AypFDF_oXjddWbwt2l0QB6h3xVM5XrBJdwVzNFckrPeg1p9pO8e6ihiftlJvX2IYNYXKkCFebYKKZcrEt3jPg-G3ijbjhEumqbgg5tvmsJ2kPVqetJwzUoewL6bqCsLUBBlmNKHkVC1dBk_cd5Ct312waFRnAYpq8FfQ",
    "expires_in": 300,
    "refresh_expires_in": 1800,
    "refresh_token": "eyJhbGciOiJIUzUxMiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJlNzVlMTQwNy03NDc4LTRhMWUtYTg0Ni1jZDc1YThhZDJjMjUifQ.eyJleHAiOjE3NjIxNzI4NjksImlhdCI6MTc2MjE3MTA2OSwianRpIjoiZmY4OWUwOWYtNTE5NS01NGIwLThkZGItNGM0MGJmNzAxNTFjIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL3JlYWxtcy91c21hbnJlYWxtIiwiYXVkIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL3JlYWxtcy91c21hbnJlYWxtIiwic3ViIjoiZjoxMjlhNTA1Yy1mNzg0LTRjZjEtYjIzMC01Yjk4NzMzMzY1MjM6MjI1NjAiLCJ0eXAiOiJSZWZyZXNoIiwiYXpwIjoidXNtYW5jbGllbnQiLCJzaWQiOiI0YWYxZGJjNy1kMThhLTJiZGQtM2RkMi1hMjdkNjkxYjRiNzIiLCJzY29wZSI6ImJhc2ljIHByb2ZpbGUgZW1haWwgd2ViLW9yaWdpbnMgYWNyIHBob25lIHJvbGVzIn0.YPzqijFZizexNo6JcHlB0pdyCsn3nTfyemL843GmFwDFshMtXIyIwOlNa8MjO3kj1NKJ5kT7hjmCQvVfhc16RA",
    "token_type": "Bearer",
    "not-before-policy": 0,
    "session_state": "4af1dbc7-d18a-2bdd-3dd2-a27d691b4b72",
    "scope": "profile email phone"
}


Note: Possible error: Password Mismatch. So you nee to insert password in encrpted form using BCrypt.
In dashboard your local Users can be found from Search option= * then enter.
And your external Users will be found as per username from serach option.




