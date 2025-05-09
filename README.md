# P6-Full-Stack-reseau-dev

# Read Me First

This document outlines the steps required to set up and run this project on your local machine.

## Prerequisites

Before starting, make sure the following are installed on your system:

1. **Java 22**  
   Check your Java version:
   ```
   java -version
   ```

2. **Maven**  
   Install Maven and check its version:
   ```
   mvn -version
   ```

3. **Git**  
   Clone the project repository with Git:
   ```
   git clone https://github.com/SivaPrinda/Developpez-une-application-full-stack-complete.git
   ```

4. **MySQL Database**  
   Set up a MySQL server and run the following file to create the database:

    - Execute the file "Test-a-full-stack-application/resources/sql/script.sql".

    - Edit the `application.properties` file with your database connection information. For example:

   ```
   spring.datasource.url=jdbc:mysql://localhost:3306/yoga?allowPublicKeyRetrieval=true
   spring.datasource.username=example
   spring.datasource.password=example123
   ```

## Building and Running the Back-end

Navigate to the project directory:

```
cd back
```

Run the following command to build the project:

```
mvn clean install
```

Then, run the following command to start the project:

```
mvn spring-boot:run
```

### MySQL Database
Set up a MySQL server
Run the file "back/src/main/resources/db/scriptTopic.sql" to create topics for the project.
Run the file "back/src/main/resources/db/scriptPost.sql" to create posts for the project.

## Building and Running the Front-end

Navigate to the project directory:

```
cd front
```

Run the following command to install project dependencies:

```
npm install
```

Then, run the following command to start the project:

```
ng serve
```

## API Documentation

This project includes an auto-generated Swagger UI for testing and exploring the API.

Once the application is running, you can access Swagger UI at:

[http://localhost:3001/swagger-ui/index.html]