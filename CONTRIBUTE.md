# How-To

### run all tests

```shell
./gradlew clean check
```

### local static analysis with qodana

```shell
docker run --rm -it -v $(pwd)/:/data/project/ -v $(pwd)/qodana/results:/data/results/ -p 8080:8080 jetbrains/qodana-jvm:latest --show-report
```

## Maintenance
### check if all dependencies are up-to-date

```shell
./gradlew dependencyUpdates
```

### auto bump all dependencies to latest stable versions and build

```shell
./gradlew updateDependencies
```

## Release
### push release to maven centrals staging repository:

```shell
./gradlew clean publishToSonatype closeSonatypeStagingRepository
```

### auto close and release artifact on maven centrals nexus:

```shell
./gradlew clean publishToSonatype closeAndReleaseSonatypeStagingRepository
```

### install project into local maven repository

```shell
./gradlew clean publishToMavenLocal
```

### update gradle wrapper

```shell
./gradlew wrapper --gradle-version 7.0.2
```