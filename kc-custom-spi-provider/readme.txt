Keycloak SPI for User Storage with Java 17 & JPA
-----------------------------------------------------
Before proceeding for SPI: Download : keycloak-26.4.1 login with admin/password and then 
create realm=usmanrealm, client=usmanclient (leave  user creation for the time being).

Custom user storage provider as per this code

put its "kc-custom-spi-provider-1.0.jar" into /providers

add the following jar into providers as well. 
-commons-dbcp2-2.13.0 
-commons-pool2-2.12.0 
-jandex-2.4.3.Final 
-jbcrypt-0.4 
-jbcrypt-0.4-sources 
-postgresql-42.7.8 
-kc-custom-spi-provider-1.0

Add follwoing properties into keyclock.conf file db-kind-user-store=postgres db-url-user-store=jdbc:postgresql://localhost:5432/collegedb db-username-user-store=college db-password-user-store=college@001

And for printing Logs: log-level=info,org.keycloak.services.resources.admin:debug,org.keycloak.authentication:debug,org.keycloak.services.resources.admin:debug,com.usman.keycloak:debug

Create new schema, user, password into postgres database: CREATE DATABASE keycloak; CREATE USER keycloak WITH PASSWORD 'password'; GRANT ALL PRIVILEGES ON DATABASE keycloak TO keycloak; ALTER DATABASE keycloak OWNER TO keycloak;

Now-> Try Login new connection for this new schema with new Users.

Create table and insert 3 sample records: CREATE TABLE myuserentity ( id VARCHAR(10) PRIMARY KEY,
username VARCHAR(50) NOT NULL UNIQUE,
password VARCHAR(255) NOT NULL,
email VARCHAR(50),
phone VARCHAR(20), firstName VARCHAR(50), lastName VARCHAR(50) );

insert into myuserentity values('101','test01','password', 'test1@gmail.com', '9900112233','test', 'test' ); insert into myuserentity values('102','test02','password', 'test2@gmail.com', '9900112222', 'test', 'test'); insert into myuserentity values('103','jamil','12345', 'jamil@gmail.com', '9900112223', 'test', 'test');

select * from myuserentity;

curl --location 'http://localhost:8080/realms/usmanrealm/protocol/openid-connect/token'
--header 'Content-Type: application/x-www-form-urlencoded'
--data-urlencode 'client_id=usmanclient'
--data-urlencode 'client_secret=xqfe5ddhQcm0xuyjzGXMDfD2KLFoLJ6i'
--data-urlencode 'grant_type=password'
--data-urlencode 'username=jamil'
--data-urlencode 'password=12345'
