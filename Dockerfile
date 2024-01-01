# Stage 1: Base image
FROM eclipse-temurin:21-jdk-alpine as base
SHELL ["/bin/bash", "-c"]
WORKDIR /var/app/
RUN <<EOF
apk add zip curl
curl -s "https://get.sdkman.io" | bash
source /root/.sdkman/bin/sdkman-init.sh 
sdk install gradle 8.5
sdk install springboot
EOF
COPY . .

# Stage 2: Build stage
FROM gradle:8.5.0-jdk21-alpine as build
WORKDIR /var/app/
COPY --from=base /var/app .
RUN ./gradlew bootJar

# Stage 3: Optimized deploy-ready image
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /var/app/
COPY --from=build /var/app/build/libs/app.jar .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

