# Store Management ![example workflow](https://github.com/filipakapog/store-management/actions/workflows/ciPipeline.yml/badge.svg?event=push)
A REST API Application for Store Management Activities

The application is written using [Java 21 Temurin](https://adoptium.net/temurin/releases/) 

# Prerequisites

You will need to have the following tools in order to build & run the application:
- [Docker Desktop](https://docs.docker.com/compose/install/)
- [Maven 3.6.3](https://maven.apache.org/docs/3.6.3/release-notes.html)

# URLs
- Keycloak Admin: http://localhost:38070/
- Keycloak Storage-Management User Login: http://localhost:38070/realms/storage-management/account 

# How to Build the Application
```bash
mvn clean install -DskipTests
docker compose -f my-app.yaml build
```


# How to start the Application
```bash
docker compose -f my-app.yaml up -d
```

# How to stop the Application
```bash
docker compose -f my-app.yaml down
```

# How to clean-up local testing environment (e.g. including volume deletion)
```bash
docker compose -f my-app.yaml down
docker volume prune -a
```

# How view app logs
```bash
docker logs storage-management -f
```
