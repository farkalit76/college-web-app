--College DB Schema---

CREATE DATABASE collegedb;

CREATE USER college WITH PASSWORD 'college@001';

GRANT ALL PRIVILEGES ON DATABASE collegedb TO college;

ALTER DATABASE collegedb OWNER TO college;

Now-> Try new connection for this new schema with new Users.

--If error persists to login the new schema=
DROP USER IF EXISTS college;

CREATE USER college WITH PASSWORD 'college@001';

CREATE DATABASE collegedb OWNER college;

GRANT ALL PRIVILEGES ON DATABASE collegedb TO college;