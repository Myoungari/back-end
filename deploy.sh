#!/bin/bash

# Create directory with proper permissions
sudo mkdir -p /database
sudo chown -R ubuntu:ubuntu /database
cd /database

if [ -f "myoungari.db" ]; then
  echo "📂 Backing up the existing SQLite database..."
  sudo mv myoungari.db myoungari.db.bak
  sudo rm -f myoungari.db
fi

echo "🚀 0. Applying the new SQLite database..."
sudo mv myoungari-new.db myoungari.db

# Set correct ownership and permissions
sudo chown ubuntu:ubuntu myoungari.db
sudo chmod 666 myoungari.db

cd ..

echo "1. Checking if the springboot container is running..."
if [ "$(docker ps -q -f name=springboot)" ]; then
  echo "Spring Boot container found. Stopping it..."
  docker-compose stop springboot
else
  echo "Spring Boot container is not running. Skipping stop step."
fi

if [ "$(docker ps -q -f name=nginx)" ]; then
  echo "Nginx container found. Stopping it..."
  docker-compose stop nginx
else
  echo "Nginx container is not running. Skipping stop step."
fi

echo "2. Starting the Spring Boot and Nginx container..."
echo "current path: $(pwd)"
docker-compose up -d springboot nginx

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
