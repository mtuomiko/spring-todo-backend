#!/bin/sh
echo "Running start script"

if [ ${ENV} = "PROD" ]; then
  export SPRING_PROFILES_ACTIVE=production
else
  export SPRING_PROFILES_ACTIVE=development
fi

# set Heroku free dyno config for Java
if [ ${ON_HEROKU} = "true" ]; then
  export JAVA_TOOL_OPTIONS="-Dfile.encoding=UTF-8 -XX:+UseContainerSupport -Xmx300m -Xss512k -XX:CICompilerCount=2"
fi

# port fallback
if [ -z "${PORT}" ]; then
  export PORT=8080
fi

exec java -Dserver.port=${PORT} -jar /app/app.jar ${@}