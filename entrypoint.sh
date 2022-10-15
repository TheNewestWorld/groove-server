#!/bin/bash

exec java \
    -Dserver.port=${PORT} \
    -Dspring.profiles.active=${PROFILE} \
    -Xms300m -Xmx300m \
    -jar /api/libs/groove-api.jar
