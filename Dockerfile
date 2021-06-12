FROM adoptopenjdk/openjdk11:alpine as build-stage
WORKDIR /app-build
COPY . .
RUN ./gradlew build

FROM adoptopenjdk/openjdk11:alpine
WORKDIR /app
RUN addgroup -S spring && adduser -S spring -G spring && chown spring /app
USER spring:spring
ARG JAR_FILE=build/libs/*.jar
COPY --from=build-stage /app-build/${JAR_FILE} app.jar
COPY --from=build-stage /app-build/run.sh .
#ENV SPRING_PROFILES_ACTIVE=production
# Default Heroku free dyno Java settings which are not present when running containers
#ENV JAVA_TOOL_OPTIONS="-Dfile.encoding=UTF-8 -XX:+UseContainerSupport -Xmx300m -Xss512k -XX:CICompilerCount=2"
ENTRYPOINT ["/app/run.sh"]

