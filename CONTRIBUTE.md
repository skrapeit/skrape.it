# How-To
## 
### run all tests

	./gradlew clean check

## Maintenance
### check if all dependencies are up-to-date

    ./gradlew dependencyUpdates

### auto bump all dependencies to latest stable versions and build

    ./gradlew updateDependencies
    
## Release
### push release to maven centrals staging repository:

	./gradlew clean uploadArchives
	
### auto close and release artifact on maven centrals nexus:

	./gradlew clean closeAndReleaseRepository
    
### install project into local maven repository

	./gradlew clean publishToMavenLocal
