## Spring Boot Todo backend with JWT and RESTful API

Should become a simple todo app backend including user auth done with JWT.

### Running

Production environment currently tied to Heroku.

#### Development

Default port is 8080. 

Run with your IDE or through `gradlew` or `gradlew.bat`, for example with `./gradlew bootRun`. In development Spring uses an H2 database which is saved to a file in the filesystem. 

Running with Docker can be done with `docker-compose up`, the default port 8080 is mapped automatically. Using a different port can be done by changing the port definitions in `docker-compose.yaml`