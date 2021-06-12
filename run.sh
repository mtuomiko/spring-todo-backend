#!/bin/sh
echo "Running start script"
if [ ${ENV} = "PROD" ]; then
  export SPRING_PROFILES_ACTIVE=production
  export JAVA_TOOL_OPTIONS="-Dfile.encoding=UTF-8 -XX:+UseContainerSupport -Xmx300m -Xss512k -XX:CICompilerCount=2"
else
  export SPRING_PROFILES_ACTIVE=default
fi

exec java -Dserver.port=${PORT} -jar /app/app.jar ${@}