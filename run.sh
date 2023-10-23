#!/bin/bash



echo "Starting kong-database..."

docker-compose up -d kong-database

STATUS="starting"

while [ "$STATUS" != "healthy" ]
do
    STATUS=$(docker inspect --format {{.State.Health.Status}} kong-database)
    echo "kong-database state = $STATUS"
    sleep 5
done


echo "Running database migrations..."

docker-compose run --rm kong kong migrations bootstrap --vv

echo "Starting kong..."

docker-compose up -d kong

echo "Kong admin running http://127.0.0.1:8001/"
echo "Kong proxy running http://127.0.0.1/"

echo "Starting konga..."
docker-compose up -d konga
echo "Konga running http://127.0.0.1:1337/"

echo "Starting file-upload-api..."
docker-compose up -d file-upload-api
echo "file-upload-api running http://127.0.0.1:8080/"

echo "Configuring Kong..."
sleep 10
curl --location 'http://127.0.0.1:8001/services' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'name=cpn_file_upload_api' \
--data-urlencode 'url=http://file-upload-api:8080'


curl -i -X POST http://localhost:8001/services/cpn_file_upload_api/routes \
  --data 'paths[]=/api' \
  --data name=fileupload_route

curl -X POST http://localhost:8001/services/cpn_file_upload_api/plugins \
    --data "name=oauth2"  \
    --data "config.scopes=cpn_user"  \
    --data "config.mandatory_scope=true"  \
    --data "config.provision_key=9lP1AUbJoqT3fdbcn08Ka9ROc2Nu3EQU"  \
    --data "config.enable_authorization_code=true"

curl --location 'http://localhost:8001/consumers' \
--header 'Content-Type: application/json' \
--data '{
    "username": "cpn-user",
    "custom_id": "cpn-custom-id"
 }'

 curl --location 'http://localhost:8001/consumers/cpn-user/oauth2' \
--header 'Content-Type: application/json' \
--data '{
    "name": "cpn-user",
    "client_id":"j7TtRUw0Px127B47hwe5YYZaYTryHJoV",
    "client_secret":"sBNS4KQiFnM22hCZkBgWIdgDwg23cOqA",
     "redirect_uris": ["https://cpn-supoer-app.store/callback"]
 }'

