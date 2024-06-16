#!/bin/bash
set -e

# Function to create a database and user
create_db_and_user() {
    local db_name=$1
    local db_user=$2
    local db_password=$3

    echo "Creating database '$db_name' and user '$db_user'"
    psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
    CREATE DATABASE "$db_name";
    CREATE USER "$db_user" WITH ENCRYPTED PASSWORD '$db_password';
    ALTER DATABASE "$db_name" OWNER TO "$db_user";
EOSQL
}

# Create databases and users
create_db_and_user $DB_FRONT_NAME $DB_FRONT_USER $DB_FRONT_PASSWORD
create_db_and_user $DB_API_NAME $DB_API_USER $DB_API_PASSWORD
