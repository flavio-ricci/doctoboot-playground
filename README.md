# doctoboot-playground

![CI tests](https://github.com/doctolib/doctoboot-playground/actions/workflows/test.yml/badge.svg)

## Introduction

Describe doctoboot-playground project!!!

## Getting Started

### Prerequisites

#### dctl (recommended)

[dctl](https://github.com/doctolib/dctl) to run `dctl devenv --profiles common` in order to ensure all the required tools are
installed to fetch various artifacts

#### Java Environment

##### Install Java 21

```shell
brew install openjdk@21
```

##### 7 - First Build

Ensure you have the latest maven settings for your workstation:

```shell
dctl devenv --profiles common
```

⚠️ all dependencies will be downloaded, it can take quite some time, (please) be patient.

```shell
./mvnw install
```

### Run tests

```shell
./mvnw clean verify
```

### Run locally

```shell
# If using database
docker-compose up -d
./mvnw spring-boot:run
```

### Run migrations (if using database)

```shell
./mvnw spring-boot:run -Dspring-boot.run.profiles=migrate
```

### Using Testcontainers at Development Time

You can run `TestApplication.java` from your IDE directly.
You can also run the application using Maven as follows:

```shell
./mvnw spring-boot:test-run
```

### Code Formatting

[DoctoBoot](https://github.com/doctolib/doctoboot) defines formatting rules
and uses [Spotless](https://github.com/diffplug/spotless/tree/main/plugin-maven) + [Palantir](https://github.com/palantir/palantir-java-format)

* Code formatting is applied by automatically by `./mwnw clean install`
* To check for formatting errors, run `./mvnw spotless:check`
* To fix locally formatting errors, run `./mvnw spotless:apply`

See the Spotless [documentation](https://github.com/diffplug/spotless/tree/main/plugin-maven#spotlessoff-and-spotlesson)
for more details

For [IntelliJ](https://www.jetbrains.com/idea/buy/) you can use:

* [Spotless Applier](https://plugins.jetbrains.com/plugin/22455-spotless-applier)
* [Palantir Java Format](https://plugins.jetbrains.com/plugin/13180-palantir-java-format)

### Useful Links

* Actuator Endpoint: http://localhost:8080/actuator

