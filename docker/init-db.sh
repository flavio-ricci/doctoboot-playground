#!/bin/sh
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" --echo-all <<-EOSQL
	CREATE USER db_user WITH PASSWORD 'db_pass';
	CREATE DATABASE "doctoboot-playground-dev";
	GRANT ALL PRIVILEGES ON DATABASE "doctoboot-playground-dev" TO db_user;
	\c doctoboot-playground-dev
	GRANT CREATE ON SCHEMA public TO db_user;
EOSQL