# How-To

### run all tests

	./gradlew clean check

### local static analysis with qodana

    docker run --rm -it -v $(pwd)/:/data/project/ -v $(pwd)/qodana/results:/data/results/ -p 8080:8080 jetbrains/qodana-jvm:latest --show-report

## Maintenance
### check if all dependencies are up-to-date

    ./gradlew dependencyUpdates

### auto bump all dependencies to latest stable versions and build

    ./gradlew updateDependencies
    
## Release
### push release to maven centrals staging repository:

	./gradlew clean publishToSonatype closeSonatypeStagingRepository
	
### auto close and release artifact on maven centrals nexus:

	./gradlew clean publishToSonatype closeAndReleaseSonatypeStagingRepository
    
### install project into local maven repository

	./gradlew clean publishToMavenLocal

### update gradle wrapper

    ./gradlew wrapper --gradle-version 7.0.2
