# Biggy Events Writer

Biggy Events Writer is the part of the Biggy system. Events Writer is responsible for reading events
from Kafka events topic and store them into MongoDB.

To build the application, build a Docker image and tag the image, type the following command:
```bash
./gradlew clean build dockerTag
```

To run the application:
```bash
docker run -d --name biggy-events-writer -v $(pwd)/configuration.yml:/app/configuration.yml --restart always -p 8080:8080 docker.przemyslawsikora.com/biggy-events-writer:<tag>
```

To allow Jenkins to build the application and publish it into Nexus, you must define following variables in Jenkins:

| Credential ID / Variable | Kind                                | Description                                              |
|--------------------------|-------------------------------------|----------------------------------------------------------|
| nexus_admin              | Credential / Username with password | Username and password to your Nexus                      |
| docker_address           | Environment variable                | Address to your Docker at Nexus, e.g. docker.example.com |
