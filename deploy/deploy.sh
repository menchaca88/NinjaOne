#!/bin/bash

set -e

# clean
docker-compose stop
docker-compose rm -f

# build
docker-compose build 

# deploy
docker-compose up -d
echo "GREEN" || echo "RED"