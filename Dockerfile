FROM azul/zulu-openjdk-alpine:11-jre
ARG JAR_FILE
COPY ${JAR_FILE} /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar", "--spring.config.location=classpath:/,file:/app/configuration.yml"]
EXPOSE 8080
