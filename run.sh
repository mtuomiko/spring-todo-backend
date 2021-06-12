#!/bin/sh
echo "Running start script"
echo "All env vars"
printenv
exec java -Dserver.port=${PORT} -jar /app/app.jar ${@}