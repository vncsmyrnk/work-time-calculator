# Work Time Calculator

This is an app built with Spring Boot and Gradle to calculate work time for a given parameter inputs.

Docker is used to handle the environments.

## Run

```bash
docker build --target base --tag wtc-image .
docker run -it --rm -v "$(pwd)":/var/app -p 8080:8080 wtc-image bash
./gradlew bootRun
```

## Development

To use the container as development environment, reuse the container created:

```bash
docker build --target base --tag wtc-image .
docker run -it --name wtc-app -v "$(pwd)":/var/app -p 8080:8080 wtc-image bash

# To run it again
docker start wtc-app

# To access it on terminal
docker exec -it wtc bash
```

To use [nvim](https://neovim.io/) inside the container for development of features, just run `.dev/nvim-setup.sh`. It will install and configure nvim and other dependencies.

This project uses hooks and actions for checking new changes. A minimum code coverage metric is used to verify the changes.

A SonarCloud action is used to check for vulnerabilities and static analysis.
