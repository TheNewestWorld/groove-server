#!/bin/bash

exec java \
    -Dserver.port=${PORT} \
    -Dspring.profiles.active=${PROFILE} \
    -jar /api/libs/groove-api.jar
