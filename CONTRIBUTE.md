### install project into local maven repository

	./gradlew clean publishToMavenLocal
	
### run all tests

	./gradlew clean check

### push release to maven central repository:

	./gradlew clean publish
	
### check if all dependencies are up-to-date

    ./gradlew dependencyUpdates
