FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY target/store-management-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080 9009
COPY scripts/entrypoint.sh .

ENV JAVA_OPTS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:9009"
CMD ["/bin/bash", "./entrypoint.sh"]