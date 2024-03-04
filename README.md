# Store Management ![example workflow](https://github.com/filipakapog/store-management/actions/workflows/ciPipeline.yml/badge.svg?event=push)
A REST API Application for Store Management Activities

The application is written using [Java 21 Temurin](https://adoptium.net/temurin/releases/) 

# Prerequisites

You will need to have the following tools in order to build & run the application:
- [Docker Desktop](https://docs.docker.com/compose/install/)
- [Maven 3.6.3](https://maven.apache.org/docs/3.6.3/release-notes.html)

# How to Build the Application
```bash
mvn clean install -DskipTests
```


# How to start the Application
```bash
docker compose -f my-app.yaml up -d
```

# How to stop the Application
```bash
docker compose -f my-app.yaml down
```

# How view app logs
```bash
docker logs storage-management -f
```
