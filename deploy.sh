#!/bin/bash

cd /home/ubuntu || exit 1

echo "1. Checking if the springboot container is running..."
if [ "$(docker ps -a -q -f name=springboot)" ]; then
  echo "Spring Boot container found. Stopping and Removing it..."
  docker compose stop springboot
  docker compose rm -f springboot
else
  echo "Spring Boot container not found. Skipping."
fi

if [ "$(docker ps -a -q -f name=nginx)" ]; then
  echo "Nginx container found. Stopping and Removing it..."
  docker compose stop nginx
  docker compose rm -f nginx
else
  echo "Nginx container is not running. Skipping."
fi

echo "2. Starting the Spring Boot and Nginx container..."
docker compose up -d springboot nginx

for cnt in {1..20}
do
  echo "3. Checking server health (${cnt}/20)..."

  REQUEST=$(curl http://127.0.0.1:8080)
  if [ -n "$REQUEST" ]; then
    echo "✅ Health check successful!"
    break
  else
    echo "❌ Server not ready yet. Retrying in 10 seconds..."
    sleep 10
  fi
done;

if [ $cnt -eq 20 ]; then
  echo "🚨 Server did not start properly! Exiting..."
  exit 1
fi

echo "✅ Deployment completed successfully!"
