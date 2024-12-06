# User Information Service

This project is a REST API service that retrieves user information from a third-party service (like GitHub) and serves it through a well-structured endpoint. It validates the user ID format and handles errors gracefully when the user ID is invalid.

## Prerequisites

- Java 17 or later installed on your system.
- Maven installed on your system.
- Redis or Docker

### 1. How to Run the Code

If you have a Git client installed, clone the repository:

```bash
git clone https://github.com/Justin-Schneider/BranchCodeChallenge.git
mvn clean install (This will also run the tests)
docker run --name redis -p 6379:6379 -d redis (or confirm your redis is running on this port)
mvn spring-boot:run
```

You verity and test the service with 
```bash
http://localhost:8080/actuator/health
http://localhost:8080/user-information/user
```