FROM adoptopenjdk/openjdk11:alpine as build-stage
WORKDIR /app-build
COPY . .
RUN ./gradlew build

FROM adoptopenjdk/openjdk11:alpine
WORKDIR /app
RUN addgroup -S spring && adduser -S spring -G spring && chown spring /app
USER spring:spring
ARG JAR_FILE=build/libs/*SNAPSHOT.jar
COPY --from=build-stage /app-build/${JAR_FILE} app.jar
COPY --from=build-stage /app-build/run.sh .
ENTRYPOINT ["/app/run.sh"]
