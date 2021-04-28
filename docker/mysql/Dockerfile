FROM mysql:latest
LABEL maintainer="Pablo Gore<pgore@innova.com.ar>"

# This file contains the initial database creation.
ADD 01_create-initial-database.sql /docker-entrypoint-initdb.d/
