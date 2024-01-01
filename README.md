# Work Time Calculator

This is an app built with Spring Boot and Gradle to calculate work time for a given parameter inputs.

## Run

```bash
docker build --target base --tag image-name .
docker run -it --rm -v "$(pwd)":/var/app -p 8080:8080 image-name bash
./gradlew bootRun
```
