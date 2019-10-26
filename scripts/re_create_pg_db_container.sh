#!/bin/bash

echo "It might be needed to re-create the SQL files to initialize the DB."
echo "If the files are OK, hit any key. If not, abort with CTRL-c"
read

DB_CONTAINER_NAME=jpa-vs-jdbc-test-db
DB_CONTAINER_PORT=54345

echo "(Re-)Create the test DB docker container '${DB_CONTAINER_NAME}' listing on '${DB_CONTAINER_PORT}'"
docker rm -f ${DB_CONTAINER_NAME} || true
docker run --name ${DB_CONTAINER_NAME} \
    --env POSTGRES_USER=testuser --env POSTGRES_PASSWORD=password --env POSTGRES_DB=testdb \
    --volume ${PWD}/db-init.sql:/docker-entrypoint-initdb.d/db-init.sql \
    --publish ${DB_CONTAINER_PORT}:5432 \
    --detach \
    postgres:9.6
echo ""
#    --volume ${PWD}/sql-init-files:/docker-entrypoint-initdb.d \

echo "Do you want to follow the container's log output?"
echo "Type 'y' for YES and any other key for no"
read -n1
echo "Received input: ${REPLY}"
if [[ ${REPLY} == 'y' ]]; then
    docker logs -f ${DB_CONTAINER_NAME}
fi
