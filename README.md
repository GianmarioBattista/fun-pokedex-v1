# fun-pokedex

```
-------- INTRO

Going back to the pokemon world: developing Pokédex APIs

This is a Java (Spring Boot) implementation. It has been designed to be simple, readable and testable.

There are going to be 2 APIs:
    1 - HTTP GET /pokemon/<pokemon name>: it retrieves the pokemon's data based on the name passed as path parameter. Pokemon's information can be retrieved from https://pokeapi.co/.
    2 - HTTP GET /pokemon/translated/<pokemon name>: it retrieves the pokemon's data based on the name passed as path parameter, but the description would be translated based on the pokemon's specs. 
        The translation will be provided by https://funtranslations.com.


-------- PROJECT STRUCTURE:

fun-pokedex/
├── src/main/java/com/pokedex/
│   ├── PokedexApplication.java
│   ├── assembler/
│   │   └── PokemonAssembler.java
│   ├── controller/
│   │   └── PokemonController.java
│   ├── command/
│   │   ├── BaseCommand.java
│   │   ├── PokemonCommand.java
│   │   └── PokemonTranslationCommand.java
│   ├── model/
│   │   └── PokemonResponseModel.java
│   ├── service/
│   │   ├── PokemonService.java
│   │   └── PokemonTranslationService.java
│   └── utils/
│       ├── PokemonUtils.java
│       └── VariableUtils.java
│
├── src/test/java/com/pokedex/
│   ├── controller/PokemonControllerTest.java
│   └── service/PokemonServiceTest.java
│
├── resources/
│   └── application.yml
│
├── pom.xml
├── Dockerfile
└── target/
    └── pokedex-1.0.0.jar


-------- PREREQUISITES

To build this project you will need: 

Java 21
Maven 3.6+
(optional) Docker & Docker Compose

-------- HOW TO RUN IT

Option 1 – Build from source

If you want to compile the project and run the tests, open your terminal and follow these steps:
mvn clean install

To generate the .jar file:
mvn clean package

Then start the application with:
java -jar target/nome-applicazione-1.0.0.jar

Option 2 – Use the pre-built JAR
For convenience, a pre-built JAR is already available under the target/ directory.
In this case, you only need Java 21 installed.

Run it directly with:
java -jar pokedex-1.0.0.jar

--------  LOCAL TESTING

If you want to change the local port or other environment settings, edit the file:
src/main/resources/application.yml

Then test the APIs locally (default port: 8080):
http :8080/pokemon/mewtwo
http :8080/pokemon/translated/mewtwo

Docker
docker build -t fun-pokedex .
docker run -p 8080:8080 fun-pokedex

-------- DIFFERENT PRODUCTION DESIGN DECISIONS
- Improved configuration management
- Using a caching system to store and retrieve recently requested data from external APIs.
- Retry mechanism or circuit breaker in case external services are down for a certain period of time or calls
- Swagger and API documentation
- Using API token for security reasons
- Global exception handler


