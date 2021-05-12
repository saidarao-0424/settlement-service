# settlement-service
# API For creation and enrichment of Settlement message using In-memory DB

### Technology Stack
- Spring Boot 2.3.0
- Java 8
- Maven 3
- H2 DB
- Open API - 3.0

### Build and run
- Import source code to IDE of your choice such as Eclipse, IntelliJ.
- `mvn clean install spring-boot:run` or we can directly run the executable (settlement-service-1.0.0-SNAPSHOT.jar) present in target dir after 'mvn clean install'

### Run the tests only
`mvn clean verify`

### Test the API
- Unit tests added to test controller and service classes
- Integration tests to load Standard Settlement Instructions and save into H2 DB.
- Since classpath has swagger GUI, we can test all the exposed endpoints using "http://localhost:9090/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Settlement%20Enrichment%20API"

