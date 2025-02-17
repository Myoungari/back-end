#!/bin/bash

echo "1. Checking if the springboot container is running..."
if [ "$(docker ps -q -f name=springboot)" ]; then
  echo "Spring Boot container found. Stopping it..."
  docker-compose stop springboot
else
  echo "Spring Boot container is not running. Skipping stop step."
fi

echo "2. Pulling the latest Spring Boot image..."
docker-compose pull springboot

echo "3. Starting the Spring Boot container..."
docker-compose up -d springboot

for cnt in {1..20}
do
  echo "4. Checking server health (${cnt}/20)..."

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
