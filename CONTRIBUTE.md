# How-To

### run all tests

```shell
./gradlew clean check
```

Gradle runs on JDK 17 or newer. The build uses a JDK 17 toolchain and targets Java 8 bytecode.

### generate API documentation

```shell
./gradlew :dokkaGeneratePublicationHtml dokkaJavadocJar
```

Dokka 2 generates Kotlin HTML documentation. The root documentation at `build/dokka/html/index.html`
combines all published modules. Maven publications include sources and this HTML documentation
in their `-sources.jar` and `-javadoc.jar` artifacts, respectively.

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
./gradlew versionCatalogUpdate
./gradlew build dokkaJavadocJar
```

## Release

Run the **Release** GitHub Actions workflow on `master`. Select `automatic` to derive the version
from conventional commits, or choose a release type. `stable` removes a pre-release suffix;
if the latest version is already stable, no release is created. The workflow builds and publishes
the selected commit, then tags that exact commit. It does not create a version-bump commit.

Local builds default to `0-SNAPSHOT`. Release versions are passed with `-PVERSION_NAME` rather
than written back into `gradle.properties`.

Configure these GitHub repository secrets before publishing:

- `MAVEN_CENTRAL_USERNAME` and `MAVEN_CENTRAL_PASSWORD`: a Central Portal user token.
- `SIGNING_IN_MEMORY_KEY`: the ASCII-armored private signing key.
- `SIGNING_IN_MEMORY_KEY_PASSWORD`: its password, if set.
- `SIGNING_IN_MEMORY_KEY_ID`: optional signing subkey ID.

The `it.skrape` namespace must be available in the Central Portal, with snapshots enabled.
Snapshots use `https://central.sonatype.com/repository/maven-snapshots/` and a version such as
`1.4.0-SNAPSHOT`, derived by incrementing the latest tag's minor version. The CI job summary
shows the version actually published. The consumer example uses a pinned, existing pre-release
and local HTML fixtures so its tests do not depend on a prior snapshot upload or external pages.

### publish to Maven Central:

```shell
./gradlew clean publishToMavenCentral -PVERSION_NAME=1.3.0 -PsignAllPublications=true
```

### publish a snapshot to Maven Central:

```shell
./gradlew clean publishToMavenCentral -PVERSION_NAME=1.3.1-SNAPSHOT -PsignAllPublications=true
```

### install project into local maven repository

```shell
./gradlew clean publishToMavenLocal
```

To inspect publications without modifying your normal Maven cache:

```shell
./gradlew publishToMavenLocal -Dmaven.repo.local="$PWD/build/maven-smoke-test"
```

### update gradle wrapper

```shell
./gradlew wrapper --gradle-version 9.5.0
```
