# swapp
A little bit of this, a little bit of that. <br>
Evergrowing, improvable MVC learning project using:
* Java 21
* Spring Boot
* Api calls to swapi: https://www.swapi.tech/
* MongoDB local access
* Sql local access
* ...
# database
* execute usersTable.sql in a sqlServer local db
* import test.movies.json to a mongoDb collection
# run
navigate so swapp root, verify you are at pom.xml level and run in terminal:<br>
./mvnw spring-boot:run <br>
* http://localhost:8080/characters -> swapi 
* http://localhost:8080/movies -> mongoDB
* http://localhost:8080/users -> sql